package com.timebusker.web;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: AuthenticationController
 * @Author: Administrator
 * @Date: 2020/1/7 15:58
 **/
@Controller
@RequestMapping("/auth")
public class AuthenticationController extends AbstractBaseController {

    private static final String LOGIN_PAGE = "/views/common/login";

    @Autowired
    private Kaptcha kaptcha;

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return LOGIN_PAGE;
    }

    /**
     * 登出
     *
     * @param mv
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mv) {
        return mv;
    }

    /**
     * 图片验证码
     *
     * @param mv
     * @return
     */
    @RequestMapping("/code")
    public void code(ModelAndView mv) {
        kaptcha.render();
    }
}
