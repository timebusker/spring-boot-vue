package com.timebusker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.*;

/**
 * @DESC:BaseController:控制层抽象父类
 * @author:timebusker
 * @date:2019/7/26
 */
public abstract class AbstractBaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected String token;

    /**
     * 在所有请求执行前，预处理
     *
     * @param request
     * @param response
     * @param session
     */
    @ModelAttribute
    public void before(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
    }
}
