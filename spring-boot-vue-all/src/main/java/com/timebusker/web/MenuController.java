package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.MenuEntity;
import com.timebusker.model.SystemEntity;
import com.timebusker.service.MenuService;
import com.timebusker.service.SystemService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: MenuController
 * @Author: Administrator
 * @Date: 2019/12/22 12:25
 **/
@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractBaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private SystemService systemService;

    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        List<MenuEntity> menus = new ArrayList<>();
        Query query = new Query(params);
        if (query.get("systemId") == null) {
            ResultVO.error("请指定系统查询菜单！");
        }
        SystemEntity system = systemService.query(query.get("systemId").toString());
        List<MenuEntity> list = menuService.query(query);
        MenuEntity menu = new MenuEntity();
        menu.setId(system.getId());
        menu.setIcon(system.getIcon());
        menu.setName(system.getName());
        menu.setChildren(list);
        menus.add(menu);
        return ResultVO.ok().put("menus", menus);
    }

    @RequestMapping("/save")
    public ResultVO save(MenuEntity menu) {
        menu.setUpdateTime(LocalDateTime.now());
        menu.setUpdateUserId("timebusker");
        menuService.save(menu);
        return ResultVO.ok();
    }
}
