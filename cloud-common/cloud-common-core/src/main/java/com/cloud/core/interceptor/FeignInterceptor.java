package com.cloud.core.interceptor;

import com.cloud.core.constant.Const;
import com.cloud.feign.util.RedisUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 设置通用签名
 *
 * @author : dayun_wang
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("access-token", redisUtils.get(Const.RedisKeyConfig.AUTHORIZATION + ":authorization").toString());
    }
}
