package com.springboot.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.jdbc.Application;
import com.springboot.jdbc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTest {

    @Resource
    private UserService userService;

    @Resource
    private JavaMailSender mailSender;

    @Before
    public void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1092221803@qq.com");
        message.setTo("facheng.wang@aliyun.com");
        message.setSubject("测试邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

    @Ignore
    @Test
    public void test() {
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);

        Assert.assertEquals(5, userService.getAllUsers().intValue());

        userService.deleteByName("a");
        userService.deleteByName("b");

        Assert.assertEquals(3, userService.getAllUsers().intValue());
    }
}
