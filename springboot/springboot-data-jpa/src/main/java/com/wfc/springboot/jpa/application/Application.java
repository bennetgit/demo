package com.wfc.springboot.jpa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by feng on 16/9/14.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.wfc.springboot.jpa.repository")
@EntityScan("com.wfc.springboot.jpa.domain")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
