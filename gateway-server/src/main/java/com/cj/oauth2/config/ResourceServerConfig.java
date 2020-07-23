package com.cj.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/24 0:12
 */
@Component
public class ResourceServerConfig {

    @Configuration
    @EnableResourceServer
    class UAAResourceServerConfig extends ResourceServerConfigurerAdapter {

    }

    @Configuration
    @EnableResourceServer
    class OrderResourceServerConfig extends ResourceServerConfigurerAdapter{

    }

}
