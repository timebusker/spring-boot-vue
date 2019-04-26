package com.timebusker.conf;

import com.timebusker.common.web.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @DESC:WebConfig
 * @author:timebusker
 * @date:2019/4/25
 */

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Autowired
    UserArgumentResolver userArgumentResolver;

    /**
     * 注册参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}
