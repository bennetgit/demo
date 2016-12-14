package com.springboot.elasticsearch.controller;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.annotation.Resource;

import com.springboot.elasticsearch.domain.UserIndex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.elasticsearch.service.IUserService;

/**
 * Created by feng on 16/12/14.
 */
@RestController
public class SearchController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/init", method = PUT)
    public void initData() {
        UserIndex user = new UserIndex();
        user.setId(100l);
        user.setAge(10);
        user.setName("hello");
        user.setSex("女");
        userService.save(user);
    }
}
