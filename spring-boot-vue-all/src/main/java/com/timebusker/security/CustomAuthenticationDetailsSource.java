package com.timebusker.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: CustomAuthenticationDetailsSource
 * 自定义表单登录意味着表单参数不定且不止账户和密码两项，但 Spring security 的WebAuthenticationDetails默认只会处理用户名和密码信息
 * 自定义表单登录可采用一下三种方式：
 * 1、ajax验证：即在提交账户密码前先把其他参数校验处理好
 * 2、在账户密码校验器，使用filter对额外参数进行校验
 * 3、扩展WebAuthenticationDetails，支持额外参数校验
 * @Author: Administrator
 * @Date: 2020/1/8 10:09
 **/
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    /**
     * 在config中使用CustomAuthenticationDetailsSource替换原有的AuthenticationDetailsSource
     *
     * @param request
     * @return
     */
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }
}
