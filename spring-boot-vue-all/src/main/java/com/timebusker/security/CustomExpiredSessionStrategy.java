package com.timebusker.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description: CustomExpiredSessionStrategy
 * 处理旧用户登陆失败的逻辑
 * @Author: Administrator
 * @Date: 2020/1/9 21:02
 **/
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        // todo 执行登出相关逻辑
    }
}
