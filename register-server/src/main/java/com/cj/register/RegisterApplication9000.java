package com.cj.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 16:41
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterApplication9000 {
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication9000.class, args);
    }
}
