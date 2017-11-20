package spring.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.demo.constant.Constants;
import spring.demo.util.SpringContextHolder;

@SpringBootApplication
// @EnableWebSocket
@EnableScheduling
@EnableWebSecurity
@EnableCaching(order = Constants.CacheConfig.CACHE_ORDER, mode = AdviceMode.ASPECTJ)
@EnableRabbit
@EnableTransactionManagement(order = Constants.TransactionConfig.ORDER, mode = AdviceMode.ASPECTJ)
@ImportResource({ "classpath:META-INF/spring/applicationContext*.xml" })
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
