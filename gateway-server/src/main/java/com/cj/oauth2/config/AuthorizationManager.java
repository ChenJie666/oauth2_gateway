package com.cj.oauth2.config;

import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/24 10:04
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        String token = authorizationContext.getExchange().getRequest().getHeaders().getFirst("Authorization");
        String realToken = token.replace("Bearer ", "");
        try {
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            System.out.println("*****userStr:" + userStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Mono<AuthorizationDecision> authorizationDecisionMono = new Mono<>();
        return authorizationDecisionMono.defaultIfEmpty(new AuthorizationDecision(false));

    }
}