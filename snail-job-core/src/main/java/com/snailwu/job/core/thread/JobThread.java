package com.snailwu.job.core.thread;

import com.snailwu.job.core.biz.model.CallbackParam;
import com.snailwu.job.core.biz.model.ResultT;
import com.snailwu.job.core.biz.model.TriggerParam;
import com.snailwu.job.core.executor.SnailJobExecutor;
import com.snailwu.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 执行定时任务
 *
 * @author 吴庆龙
 * @date 2020/5/26 12:22 下午
 */
public class JobThread extends Thread {
    public static final Logger log = LoggerFactory.getLogger(JobThread.class);

    /**
     * 任务的 id
     */
    private final int jobId;

    /**
     * 任务对应的 Handler
     */
    private final IJobHandler jobHandler;

    /**
     * 该线程要执行的任务队列
     */
    private final LinkedBlockingQueue<TriggerParam> triggerQueue;

//    private final Set<Long> triggerLogIdSet;

    /**
     * 是否退出 run 方法的 while 循环
     */
    private volatile boolean stopFlag = false;

    /**
     * 停止 while 循环的原因
     */
    private String stopReason;

    /**
     * 是否正在执行任务
     */
    private boolean runningFlag = false;

    /**
     * 从队列中获取任务失败的次数，达到指定次数后则将该线程停止
     */
    private int idleTimes = 0;

    public JobThread(int jobId, IJobHandler jobHandler) {
        this.jobId = jobId;
        this.jobHandler = jobHandler;

        this.triggerQueue = new LinkedBlockingQueue<>();
//        this.triggerLogIdSet = Collections.synchronizedSet(new HashSet<>());
    }

    /**
     * 获取 JobHandler
     */
    public IJobHandler getJobHandler() {
        return jobHandler;
    }

    /**
     * 添加任务到任务队列中
     */
    public ResultT<String> pushTriggerQueue(TriggerParam triggerParam) {
//        if (triggerLogIdSet.contains(triggerParam.getLogId())) {
//            log.info("repeat trigger job, logId:{}", triggerParam.getLogId());
//            return new ResultT<>(ResultT.FAIL_CODE, "repeat trigger job, logId:" + triggerParam.getLogId());
//        }
//        triggerLogIdSet.add(triggerParam.getLogId());

        // 放入无界队列中
        triggerQueue.add(triggerParam);
        return ResultT.SUCCESS;
    }

    /**
     * 该 JobThread 是否忙碌
     */
    public boolean isRunningOrHasQueue() {
        return runningFlag || triggerQueue.size() > 0;
    }

    @Override
    public void run() {
        // 执行该任务的初始化方法
        try {
            jobHandler.init();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        // 线程启动后不断轮训队列，有任务就执行，没有任务则累加次数达到 30 次就将该线程停止
        while (!stopFlag) {
            // 没有运行任务
            runningFlag = false;
            // 累加无任务次数
            idleTimes++;

            // 任务参数
            TriggerParam triggerParam = null;
            // 任务执行结果
            ResultT<String> executeResult = null;

            try {
                // 从队列中获取任务
                triggerParam = triggerQueue.poll(3L, TimeUnit.SECONDS);
                if (triggerParam != null) { // 有任务需要执行
                    // 设置为运行任务中
                    runningFlag = true;
                    // 重置无任务次数
                    idleTimes = 0;
//                    triggerLogIdSet.remove(triggerParam.getLogId());

                    if (triggerParam.getExecutorTimeout() > 0) { // 有超时时间的
                        final TriggerParam triggerParamTmp = triggerParam;
                        FutureTask<ResultT<String>> futureTask = new FutureTask<>(new Callable<ResultT<String>>() {
                            @Override
                            public ResultT<String> call() throws Exception {
                                return jobHandler.execute(triggerParamTmp.getExecutorParams());
                            }
                        });
                        Thread futureThead = new Thread(futureTask);
                        futureThead.start();

                        // 获取执行结果
                        try {
                            executeResult = futureTask.get(triggerParam.getExecutorTimeout(), TimeUnit.SECONDS);
                        } catch (TimeoutException e) {
                            log.error("snail-job job execute timeout");
                            executeResult = new ResultT<>(ResultT.FAIL_CODE, "job execute timeout");
                        } finally {
                            // 发生异常时，终止线程的挂起操作，让线程自行结束
                            // 线程内如果用 while(true) 的话，interrupt 方法不能停止线程
                            futureThead.interrupt();
                        }
                    } else { // 没有超时时间的，直接执行
                        executeResult = jobHandler.execute(triggerParam.getExecutorParams());
                    }
                    log.info("snail-job job execute end(finish)");
                } else {
                    if (idleTimes > 30 && triggerQueue.size() == 0) {
                        // 30 次获取操作后，没有任务可以处理，则自动销毁线程
                        SnailJobExecutor.removeJobThread(jobId, "executor idle time over limit");
                    }
                }
            } catch (Exception e) {
                if (stopFlag) {
                    // 线程停止时抛出的异常忽略
                    log.info("JobThread toStop, stopReason:{}", stopReason);
                } else {
                    // 线程未停止时抛出了异常需要打印
                    log.error("JobThread 异常结束.", e);
                }
            } finally {
                if (triggerParam != null) {
                    if (!stopFlag) {
                        CallbackParam callbackParam = new CallbackParam(triggerParam.getLogId(), triggerParam.getLogDateTime(), executeResult);
                        TriggerCallbackThread.pushCallback(callbackParam);
                    } else {
                        // 线程被杀死了
                        ResultT<String> stopResult = new ResultT<>(ResultT.FAIL_CODE,
                                stopReason + "[job running, killed]");
                        CallbackParam callbackParam = new CallbackParam(triggerParam.getLogId(), triggerParam.getLogDateTime(), stopResult);
                        TriggerCallbackThread.pushCallback(callbackParam);
                    }
                }
            }
        }

        // 线程被终止，但是还有待执行的任务在队列中
        while (triggerQueue.size() > 0) {
            TriggerParam triggerParam = triggerQueue.poll();
            if (triggerParam != null) {
                ResultT<String> resultT = new ResultT<>(ResultT.FAIL_CODE,
                        stopReason + " [job not executed, in the job queue, killed.]");
                CallbackParam callbackParam = new CallbackParam(triggerParam.getLogId(), triggerParam.getLogDateTime(), resultT);
                TriggerCallbackThread.pushCallback(callbackParam);
            }
        }

        // 执行该任务的销毁方法
        try {
            jobHandler.destroy();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Thread.interrupt 只支持终止线程的阻塞状态(wait、join、sleep)，
     * 在阻塞出抛出 InterruptedException 异常,但是并不会终止运行的线程本身；
     * 所以需要注意，此处彻底销毁本线程，需要通过共享变量方式；
     */
    public void toStop(String stopReason) {
        this.stopFlag = true;
        this.stopReason = stopReason;
    }
}