package com.cloud.feign.service;

import com.cloud.core.interceptor.FeignInterceptor;
import com.cloud.feign.hystrix.UserFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 获取用户信息
 *
 * @author dayun_wang
 */
@FeignClient(name = "feign-service-user",configuration = FeignInterceptor.class, fallback = UserFeignApiHystrix.class)
public interface UserFeignApi {

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "getUserMessage")
    List getUserMessage();

    /**
     * 获取测试信息
     *
     * @return
     */
    @RequestMapping(value = "getTestMessage")
    List getTestMessage();
}
