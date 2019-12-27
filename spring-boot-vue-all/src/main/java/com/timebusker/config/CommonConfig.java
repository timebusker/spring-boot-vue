package com.timebusker.config;

import com.timebusker.interceptor.BaseWebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: WebConfig
 * @Author: Administrator
 * @Date: 2019/12/24 14:41
 **/
@Configuration
public class CommonConfig implements WebMvcConfigurer {

    @Autowired
    private BaseWebInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
