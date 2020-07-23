package com.cj.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:39
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cj.oauth2.dao")
public class UaaApplication9500 {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication9500.class, args);
    }
}
