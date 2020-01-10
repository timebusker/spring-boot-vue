package com.timebusker.security;

import com.timebusker.common.web.ResultVO;
import com.timebusker.utils.HttpServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: CustomAccessDeniedHandler:处理权限不足异常处理
 * @Author: Administrator
 * @Date: 2020/1/10 11:30
 **/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        System.err.println("\t\t\t处理权限不足异常处理！");
        HttpServletUtil.writeJson(response, ResultVO.error(403, exception.getMessage()));
    }
}
