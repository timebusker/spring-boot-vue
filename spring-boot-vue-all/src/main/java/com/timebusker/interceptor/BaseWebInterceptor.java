package com.timebusker.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: BaseWebInterceptor
 * @Author: Administrator
 * @Date: 2019/12/24 14:42
 **/
@Component
public class BaseWebInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BaseWebInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("Controller执行前\t---本次请求路径是：" + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("Controller执行后\t---本次请求路径是：" + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("Controller执行后页面渲染前\t---本次请求路径是：" + request.getRequestURI());
    }
}
