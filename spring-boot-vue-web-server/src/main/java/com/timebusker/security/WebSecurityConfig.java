package com.timebusker.security;

import com.timebusker.security.authentication.*;
import com.timebusker.security.service.CustomUserDetailService;
import com.timebusker.security.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @DESC:WebSecurityConfig
 * @author:timebusker
 * @date:2019/4/26
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login*", "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF
                .csrf().disable()
                // 授权异常
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                // 用户认证信息已经持久化到redis,不在启用创建session会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置登录操作
                .formLogin().loginPage("/login").successHandler(customAuthenticationSuccessHandler).failureHandler(customAuthenticationFailureHandler).permitAll()
                // 过滤请求
                .and()
                .authorizeRequests().antMatchers("/login*", "/static/**").permitAll()
                // 任何人都可以访问
                .antMatchers("/menu/**").anonymous()
                .antMatchers("/file/**").anonymous()
                .antMatchers("/shop/**").anonymous()
                // 其他所有请求都需要认证
                .anyRequest().authenticated();

    }

    /**
     * 设置权限配置前缀，默认：ROLE_
     *
     * @return
     */
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    /**
     * 用户认证接口
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }
}
