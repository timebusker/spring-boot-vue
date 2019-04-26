package com.timebusker.web;

import com.timebusker.common.redis.RedisService;
import com.timebusker.constant.CommonKey;
import com.timebusker.model.common.SysUser;
import com.timebusker.service.cache.SysUserKey;
import com.timebusker.service.common.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.*;

/**
 * @DESC:AbstractBaseController
 * @author:timebusker
 * @date:2019/3/21
 */
public abstract class AbstractBaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected String token;

    @Autowired
    protected RedisService redisService;

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

    @ModelAttribute
    public void token() {
        token = request.getParameter(CommonKey.COOKIE_NAME_TOKEN);
        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CommonKey.COOKIE_NAME_TOKEN)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
    }

    @ModelAttribute
    public void setUserCookie(SysUser user) {
        if (user != null) {
            redisService.set(SysUserKey.TOKEN, token, user);
            addCookie(CommonKey.COOKIE_NAME_TOKEN, token);
        } else {
            // 及时删除cookie
            addCookie(CommonKey.COOKIE_NAME_TOKEN, "", 0);
        }
    }

    protected void addCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(SysUserKey.TOKEN.getExpire());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    protected void addCookie(String cookieName, String cookieValue, int expire) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(expire);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
