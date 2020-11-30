package com.snailwu.job.core.biz.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.snailwu.job.core.biz.AdminBiz;
import com.snailwu.job.core.biz.model.CallbackParam;
import com.snailwu.job.core.biz.model.RegistryParam;
import com.snailwu.job.core.biz.model.ResultT;
import com.snailwu.job.core.utils.SnailJobHttpUtil;
import com.snailwu.job.core.utils.SnailJobJsonUtil;

import static com.snailwu.job.core.constants.JobCoreConstant.URL_SEPARATOR;

/**
 * Core 调用 Admin 接口的 Client
 *
 * @author 吴庆龙
 * @date 2020/5/25 2:50 下午
 */
public class AdminBizClient implements AdminBiz {

    /**
     * 调度中心的地址
     */
    private final String address;

    /**
     * AccessToken
     */
    private final String accessToken;

    public AdminBizClient(String address, String accessToken) {
        if (!address.endsWith(URL_SEPARATOR)) {
            address = address + URL_SEPARATOR;
        }

        this.address = address;
        this.accessToken = accessToken;
    }

    @Override
    public ResultT<String> callback(CallbackParam callbackParam) {
        String respContent = SnailJobHttpUtil.post(address + "api/callback", accessToken, callbackParam);
        return SnailJobJsonUtil.readValue(respContent, new TypeReference<ResultT<String>>() {
        });
    }

    @Override
    public ResultT<String> registryNode(RegistryParam registryParam) {
        String respContent = SnailJobHttpUtil.post(address + "api/registry", accessToken, registryParam);
        return SnailJobJsonUtil.readValue(respContent, new TypeReference<ResultT<String>>() {
        });
    }

    @Override
    public ResultT<String> removeNode(RegistryParam registryParam) {
        String respContent = SnailJobHttpUtil.post(address + "api/registryRemove", accessToken, registryParam);
        return SnailJobJsonUtil.readValue(respContent, new TypeReference<ResultT<String>>() {
        });
    }
}