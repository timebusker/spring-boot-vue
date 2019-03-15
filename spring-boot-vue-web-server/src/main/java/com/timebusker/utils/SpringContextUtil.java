package com.timebusker.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @DESC:SpringContextUtils
 * @author:timebusker
 * @date:2019/3/14
 */
@Component
public class SpringContextUtil {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            throw new RuntimeException("context未注入!");
        }
        return context.getBean(clazz);
    }

    public static <T> T getBean(String beanId, Class<T> clazz) {
        if (context == null) {
            throw new RuntimeException("context未注入!");
        }
        return context.getBean(beanId, clazz);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.context = applicationContext;
    }
}
