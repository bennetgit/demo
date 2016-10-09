package com.springboot.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @Autowired
    private TestService service;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/hello")
    public String index(){
        System.out.println("Start");
        service.toString();
        System.out.println(this.toString() + "1111" + request.toString());
        return "Hello World!";
    }
}
