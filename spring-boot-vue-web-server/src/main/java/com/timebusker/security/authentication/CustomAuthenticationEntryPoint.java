package com.timebusker.security.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @DESC:UnAuthorizedErrorHandler:当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
 * <p>
 * 与实现AccessDeniedHandler接口功能类似
 * @author:timebusker
 * @date:2019/4/30
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     * 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
     * <p>
     * 默认情况下登陆失败会跳转页面，这里自定义，同时判断是否ajax请求，是ajax请求则返回json，否则跳转失败页面
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException == null ? "Unauthorized" : authException.getMessage());
    }
}
