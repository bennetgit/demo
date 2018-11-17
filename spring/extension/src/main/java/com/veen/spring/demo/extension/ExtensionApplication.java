package com.veen.spring.demo.extension;

import com.veen.spring.demo.extension.model.People;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class ExtensionApplication {

    public static void main(String[] args) {
        // SpringApplication.run(ExtensionApplication.class, args);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = (People) applicationContext.getBean("demoPeople");
        System.out.println(people);
    }
}
