//package spring.demo.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * Created by facheng on 17.03.17.
// */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ConfigurationProperties
//public class WebSecurityConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("admin/login");
//        registry.addViewController("/loginLimit").setViewName("error/loginLimit");
//    }
//}
//
