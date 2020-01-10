package com.timebusker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * @Description: CustomObjectPostProcessor
 * @Author: Administrator
 * @Date: 2020/1/10 15:00
 **/
public class CustomObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    private CustomFilterInvocationSecurityMetadataSource invocationSecurityMetadataSource;

    private CustomAccessDecisionManager accessDecisionManager;

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
        object.setAccessDecisionManager(accessDecisionManager);
        object.setSecurityMetadataSource(invocationSecurityMetadataSource);
        return object;
    }
}
