package com.cloud.zuul;

import com.cloud.zuul.filter.AccessAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 路由转发
 *
 * @author : dayun_wang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

    /**
     * 过滤器
     *
     * @return
     */
    @Bean
    public AccessAuthFilter accessFilter() {
        return new AccessAuthFilter();
    }
}
