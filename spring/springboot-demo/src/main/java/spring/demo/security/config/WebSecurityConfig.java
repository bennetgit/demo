package spring.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import spring.demo.security.service.MyAccessDecisionManager;
import spring.demo.security.service.MyAuthenticationProvider;
import spring.demo.security.service.MySessionRegistry;
import spring.demo.security.service.MyUserDetailsService;
import spring.demo.security.success.MyLoginSuccessHandler;
import spring.demo.security.success.MyLogoutSuccessHandler;

/**
 * Created by facheng on 17.03.17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()// 拦截页面
                .anyRequest().authenticated()// 全部页面都要验证
                .accessDecisionManager(myAccessDecisionManager());// 使用自定义拦截

        http.csrf()// 禁用csrf - 使用自定义登录页面
                .disable();

        http.formLogin()// 登录
                // .loginPage("/account/login.html").usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/index")// 先defaultSuccessUrl后successHandler，不然successHandler不会执行
                .successHandler(myLoginSuccessHandler())
                .withObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {// 高级设置-拦截器
                    public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O fsi) {
                        return fsi;
                    }

                }).permitAll();

        http.logout()// 登出
                .logoutUrl("/demo/logout").logoutSuccessUrl("/demo/login")
                .logoutSuccessHandler(myLogoutSuccessHandler()).deleteCookies("remember-me").permitAll();

        http.rememberMe()// 记住我
                .rememberMeCookieName("remember-me").userDetailsService(userDetailService());

        // http.sessionManagement()// Session管理器
        // .sessionFixation().changeSessionId().sessionAuthenticationErrorUrl("/account/log3in.html")
        // .invalidSessionUrl("/account/log1in.html")// Session失效
        // .maximumSessions(1)// 只能同时一个人在线
        // .sessionRegistry(mySessionRegistry())// 启用这个让maximumSessions生效
        // .expiredUrl("/account/log2in.html");

        http.exceptionHandling()// 权限验证失败进入的页面（只对使用自定义拦截有效）
                .accessDeniedPage("/error/access_denied.html");

        http.headers()// 允许同源iframe访问
                .frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider());
    }

    @Bean
    MyLoginSuccessHandler myLoginSuccessHandler() {
        return new MyLoginSuccessHandler();
    }

    @Bean
    MyLogoutSuccessHandler myLogoutSuccessHandler() {
        return new MyLogoutSuccessHandler();
    }

    /**
     * 自定义验证
     */
    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        return new MyAuthenticationProvider();
    }

    @Bean
    MyUserDetailsService userDetailService() {
        return new MyUserDetailsService();
    }

    @Bean
    MySessionRegistry mySessionRegistry() {
        return new MySessionRegistry();
    }

    @Bean
    MyAccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();
    }
}
