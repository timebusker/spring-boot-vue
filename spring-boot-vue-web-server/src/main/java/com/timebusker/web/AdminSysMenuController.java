package com.timebusker.web;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.web.ResponseBean;
import com.timebusker.model.SysMenu;
import com.timebusker.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @DESC:AdminSysMenuController:系统菜单前端控制器
 * @author:timebusker
 * @date:2019/3/22
 */
@RestController
@RequestMapping("/menu")
public class AdminSysMenuController extends AbstractBaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseBean list(HashMap<String, Object> params) {
        List<SysMenu> list = sysMenuService.getMenusList(params);
        System.err.println(JSON.toJSONString(list));
        return ResponseBean.ok().put("list", list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseBean getMenu(@PathVariable long id) {
        SysMenu menu = sysMenuService.getMenuById(id);
        return ResponseBean.ok().put("menu", menu);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseBean save(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return ResponseBean.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseBean delete(@RequestBody SysMenu menu) {
        sysMenuService.deleteMenu(menu);
        return ResponseBean.ok();
    }
}
