package com.wfc.test;

import com.wfc.springboot.jpa.application.Application;
import com.wfc.springboot.jpa.domain.TestUser;
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

        TestUser user1 = new TestUser();
        user1.setName("hhhh");
        user1.setAge(112);

        repository.save(user1);

        List<TestUser> users = repository.findAll();
        System.out.println("xxxxxxxxxxx"+users);
    }
}
