package com.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 聚合监控
 *
 * @author dayun_wang
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
@EnableTurbine
public class CloudTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudTurbineApplication.class, args);
    }
}
