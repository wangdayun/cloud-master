package com.cloud.core.interceptor;

import com.cloud.core.common.JwtKeyUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 设置通用签名
 *
 * @author : dayun_wang
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("x-label", JwtKeyUtils.getToken(UUID.randomUUID().toString()));
    }
}
