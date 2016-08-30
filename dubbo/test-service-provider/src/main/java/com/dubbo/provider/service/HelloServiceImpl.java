package com.dubbo.provider.service;

import com.dubbo.exchange.HelloService;

public class HelloServiceImpl implements HelloService {

	public void sayHello() {
		System.out.println("hello");
	}

}
