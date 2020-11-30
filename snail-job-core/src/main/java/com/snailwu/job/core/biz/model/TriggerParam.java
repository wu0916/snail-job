package com.snailwu.job.core.biz.model;

/**
 * @author 吴庆龙
 * @date 2020/5/25 12:05 下午
 */
public class TriggerParam {

    /**
     * 任务 ID
     */
    private Integer jobId;

    /**
     * 执行 handler、参数、堵塞策略、超时时间
     */
    private String executorHandler;
    private String executorParams;
    private String executorBlockStrategy;
    private Integer executorTimeout;

    /**
     * 任务对应的日志 ID 和 时间。用于进行回调，填充日志
     */
    private Long logId;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParams() {
        return executorParams;
    }

    public void setExecutorParams(String executorParams) {
        this.executorParams = executorParams;
    }

    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TriggerParam that = (TriggerParam) o;
        if (!jobId.equals(that.jobId)) {
            return false;
        }
        return logId.equals(that.logId);
    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + logId.hashCode();
        return result;
    }

}
