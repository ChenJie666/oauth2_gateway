package com.cj.oauth2.config;

import com.sun.deploy.util.ArrayUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/24 0:12
 */
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Configuration
    @EnableResourceServer
    class UAAResourceServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            super.configure(resources);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            super.configure(http);
        }
    }

    @Configuration
    @EnableResourceServer
    class OrderResourceServerConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            super.configure(resources);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            super.configure(http);
        }
    }



}
