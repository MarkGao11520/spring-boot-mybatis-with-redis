package com.gwf.family.configurer;

import com.gwf.family.business.core.components.JwtBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Configuration
@EnableWebSecurity   //开启WebSecurity支持
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启prePostEnabled注解支持
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService jwtUserDetailsServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密的bean，使用BCrypt
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 前置过滤器
     * @return
     */
    @Bean
    JwtAuthenticationTokenFilter authenticationTokenFilterBean(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    JwtBean jwtBean(){
        return new JwtBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()  //禁用csrf保护
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //禁用session
                .and()
                .authorizeRequests()  //所有请求都要验证
                .antMatchers("/auth/**").permitAll()  //登录注册等请求过滤
                .antMatchers("/code/**").permitAll()  //登录注册等请求过滤
                .antMatchers(
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css"
                ).permitAll()  //静态资源过滤
                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling()  //验证不通过的配置
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                ;
        http   //添加前置过滤器
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http   //禁用header缓存
                .headers().cacheControl();
    }
}
