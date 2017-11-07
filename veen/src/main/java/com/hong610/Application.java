package com.hong610;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"com.hong610"})//扫描包
@ImportResource({"classpath:xml/application.xml"})
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 注册Servlet kaptcha 验证码插件
	 */
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new KaptchaServlet());
		registration.addUrlMappings("/captcha.jpg");
		registration.addInitParameter("kaptcha.image.width", "206");
		registration.addInitParameter("kaptcha.image.height", "41");
		registration.addInitParameter("kaptcha.textproducer.font.size", "30");
		registration.addInitParameter("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		return registration;
	}
}
