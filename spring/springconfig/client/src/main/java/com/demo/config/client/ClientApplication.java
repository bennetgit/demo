package com.demo.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ClientApplication {

    @Value("${evn}")
    private String evn;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
