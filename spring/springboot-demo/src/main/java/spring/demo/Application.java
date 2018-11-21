package spring.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.demo.constant.Constants.CacheConfig;
import spring.demo.constant.Constants.TransactionConfig;
import spring.demo.exercise.specialbean.DisposableBeanTest;
import spring.demo.util.SpringContextHolder;

@SpringBootApplication
// @EnableWebSocket
@EnableScheduling
@EnableWebSecurity
@EnableAsync(mode = AdviceMode.PROXY)
@EnableCaching(order = CacheConfig.CACHE_ORDER, mode = AdviceMode.PROXY)
@EnableRabbit
@EnableTransactionManagement(order = TransactionConfig.ORDER, mode = AdviceMode.PROXY)
@EnableRedisHttpSession
@ImportResource({ "classpath:META-INF/spring/applicationContext*.xml" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    // test for disposable bean
    public DisposableBeanTest getDisposableBeanTest() {
        return new DisposableBeanTest();
    }

}
