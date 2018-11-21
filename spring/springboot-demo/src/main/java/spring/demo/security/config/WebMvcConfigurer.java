package spring.demo.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import spring.demo.security.resolver.PageQueryHandlerMethodArgumentResolver;

/**
 * Created by wangfacheng on 2017-11-15.
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pagequeryhandlerMethodArgumentResolver());
    }

    @Bean
    PageQueryHandlerMethodArgumentResolver pagequeryhandlerMethodArgumentResolver() {
        return new PageQueryHandlerMethodArgumentResolver();
    }

    // spring session 默认策略
    // 可选策略 HeaderHttpSessionStrategy MultiHttpSessionStrategyAdapter
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new CookieHttpSessionStrategy();
    }

}
