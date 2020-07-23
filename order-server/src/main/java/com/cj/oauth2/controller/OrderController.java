package com.cj.oauth2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 23:30
 */
@RestController
public class OrderController {

    @GetMapping(value = "/list")
    public String list(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name + "访问list资源";
    }

    @GetMapping(value = "/r/users")
    public String users(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name + "访问users资源";
    }

}
