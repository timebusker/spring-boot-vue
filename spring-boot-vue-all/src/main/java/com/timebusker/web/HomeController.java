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
@RequestMapping("/")
public class HomeController extends AbstractBaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping({"/", "", "/index", "/home"})
    public String index() {
        return "index";
    }

    @RequestMapping("/menus")
    @ResponseBody
    public ResultVO list() {
        Query query = new Query();
        List<MenuEntity> list = menuService.query(query);
        return ResultVO.ok().put("menus", list);
    }
}
