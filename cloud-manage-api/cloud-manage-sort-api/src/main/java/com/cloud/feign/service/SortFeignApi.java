package com.cloud.feign.service;

import com.cloud.core.interceptor.FeignInterceptor;
import com.cloud.feign.hystrix.SortFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 获取分类信息
 *
 * @author dayun_wang
 */
@FeignClient(name = "feign-service-sort", configuration = FeignInterceptor.class, fallback = SortFeignApiHystrix.class)
public interface SortFeignApi {

    /**
     * 获取分类信息
     *
     * @return
     */
    @RequestMapping(value = "getSortMessage")
    List getSortMessage();
}
