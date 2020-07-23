package com.cj.oauth2.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> feignException(Exception exception) {
        String msg = exception.getMessage();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg",msg);
        return map;
    }

}
