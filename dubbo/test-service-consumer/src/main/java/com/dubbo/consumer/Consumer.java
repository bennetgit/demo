package com.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.exchange.HelloService;

public class Consumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		HelloService helloService = (HelloService) context.getBean("helloService");
		helloService.sayHello();
	}
}
