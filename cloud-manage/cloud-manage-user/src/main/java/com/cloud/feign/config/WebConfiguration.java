package com.cloud.feign.config;

import com.cloud.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器配置
 *
 * @author dayun_wang
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void  addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/swagger-resources/**","*.js","/**/*.js","*.css","/**/*.css","*.html","/**/*.html");
    }

//    public void configureMessageConverters(List<HttpMessageConverter<?>> converterList){
//        HttpMessageConverterConfig.converterConfig(converterList);
//    }
}
