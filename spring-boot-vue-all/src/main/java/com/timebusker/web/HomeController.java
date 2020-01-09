package com.timebusker.web;

import com.timebusker.common.SystemConstant;
import com.timebusker.common.web.ResultVO;
import com.timebusker.model.MenuEntity;
import com.timebusker.service.MenuService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @DESC:HomeController：首页控制层
 * @author:timebusker
 * @date:2019/7/26
 */
@Controller
@RequestMapping({"/index", "/home"})
public class HomeController extends AbstractBaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 项目自动加载： Adding welcome page: class path resource [templates/index.html]
     * 此处主要作为补充：
     * 模板页面已配置到index.html,增加此处主要是方便页面跳转
     *
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public ResultVO list() {
        Query query = new Query();
        List<MenuEntity> list = menuService.query(query);
        return ResultVO.ok().put("menus", list);
    }
}
