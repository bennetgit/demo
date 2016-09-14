package com.wfc.test;

import com.wfc.springboot.jpa.application.Application;
import com.wfc.springboot.jpa.domain.User;
import com.wfc.springboot.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by feng on 16/9/14.
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testUser(){

        User user = new User();
        user.setAge(123);
        user.setName("hello11");

        repository.save(user);

        List<User> users = repository.findAll();
        System.out.println("xxxxxxxxxxx"+users);
    }
}
