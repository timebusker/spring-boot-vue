package com.timebusker.web.common;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.web.ResultVo;
import com.timebusker.model.common.SysMenu;
import com.timebusker.service.common.SysMenuService;
import com.timebusker.web.AbstractBaseController;
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
    public ResultVo list(HashMap<String, Object> params) {
        List<SysMenu> list = sysMenuService.getMenusList(params);
        System.err.println(JSON.toJSONString(list));
        return ResultVo.ok().put("list", list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVo getMenu(@PathVariable long id) {
        SysMenu menu = sysMenuService.getMenuById(id);
        return ResultVo.ok().put("menu", menu);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultVo save(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return ResultVo.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultVo delete(@RequestBody SysMenu menu) {
        sysMenuService.deleteMenu(menu);
        return ResultVo.ok();
    }
}
