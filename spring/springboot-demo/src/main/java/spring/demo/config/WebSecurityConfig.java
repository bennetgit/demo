package spring.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by facheng on 17.03.17.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    // registry.addViewController("/login").setViewName("login");
    // }
    //
    // @Bean
    // public ApplicationSecurity applicationSecurity() {
    // return new ApplicationSecurity();
    // }
    //
    // @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    // protected static class ApplicationSecurity extends
    // WebSecurityConfigurerAdapter {
    //
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests().antMatchers("/js/**", "/css/**",
    // "/plugins/**").permitAll().anyRequest()
    // .fullyAuthenticated().and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
    // .and().logout().permitAll();
    // http.csrf().disable();
    // }
    //
    // @Override
    // public void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER");
    // auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    // }
    //
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/js/**", "/css/**", "/plugins/**","/websocket").permitAll().anyRequest()
                .authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        http.authorizeRequests().antMatchers("/js/**", "/css/**", "/plugins/**").permitAll().anyRequest()
                .fullyAuthenticated().and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
                .logout().permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
    }
}
