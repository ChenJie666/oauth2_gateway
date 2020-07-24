package com.cj.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;


/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 17:01
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    //注入编码解码器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 从数据库中获取Details信息并指定密码的编码解码器（客户端配置和端点配置都会用到）
     *
     * @param datasource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource datasource) {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(datasource);
        jdbcClientDetailsService.setPasswordEncoder(bCryptPasswordEncoder());
        return jdbcClientDetailsService;
    }

    @Resource
    private ClientDetailsService clientDetailsService;

    /**
     * 第一部分：客户端信息从数据库读取
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 第二部分：令牌访问端点配置(令牌的颁发、认证，包括客户端配置，授权码配置)
     */
    //获得Token的配置(以JWT作为令牌并指定秘钥)
    @Resource
    private TokenStore tokenStore;
    //获得JWT令牌的配置(指定秘钥)
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    //获得认证管理器，用于对token进行校验
    @Autowired
    private AuthenticationManager authenticationManager;

    //配置在数据库中存储和读取授权码
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices services = new DefaultTokenServices(); //AuthorizationServerTokenServices的实现类
        services.setClientDetailsService(clientDetailsService); //客户端信息和端点信息都从数据库中获取
        services.setSupportRefreshToken(true); //支持刷新令牌
        services.setTokenStore(tokenStore);
        //令牌增强，设置JWT令牌
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        services.setAccessTokenValiditySeconds(7200); //令牌默认有效时间2小时
        services.setRefreshTokenValiditySeconds(259200); //刷新令牌默认有效期3天

        return services;
    }

    //令牌访问端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //使用认证管理器进行认证
                .authorizationCodeServices(authorizationCodeServices) //授权码服务配置
                .tokenServices(authorizationServerTokenServices()) //令牌管理服务（设置令牌类型JWT、令牌加密秘钥和令牌存储方式）
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许请求的方式为POST

    }

    /**
     * 第三部分：安全约束配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()") // /auth/token_key是公开的
                .checkTokenAccess("permitAll()") // /auth/check_token是公开的
                .allowFormAuthenticationForClients(); //允许表单认证(申请令牌)
    }
}
