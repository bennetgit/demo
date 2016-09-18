package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by feng on 16/9/19.
 */

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("host","https://www.baidu.com");
        return "index";
    }
}
