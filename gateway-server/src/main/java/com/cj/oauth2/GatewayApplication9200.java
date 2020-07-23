package com.cj.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/24 0:12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication9200 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication9200.class, args);
    }
}
