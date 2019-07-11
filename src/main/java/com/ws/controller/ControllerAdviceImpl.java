package com.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdviceImpl {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handler(Exception e){

        HashMap<Object, Object> map = new HashMap<>(4);
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("message",e.getMessage());

        return map;
    }


}
