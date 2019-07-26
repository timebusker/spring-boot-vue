package com.timebusker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @DESC:HomeController：首页控制层
 * @author:timebusker
 * @date:2019/7/26
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractBaseController {

    @RequestMapping({"/", "", "/index", "/home"})
    public Object index() {
        return "index";
    }
}
