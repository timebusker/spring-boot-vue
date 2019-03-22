package com.timebusker.web;

import com.timebusker.common.web.ResponseBean;
import com.timebusker.model.SysMenu;
import com.timebusker.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseBean list() {
        List<SysMenu> list = sysMenuService.getMenusList(null);
        return ResponseBean.ok().put("list", list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseBean add(@RequestBody SysMenu menu) {
        sysMenuService.AddMenu(menu);
        return ResponseBean.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseBean update(@RequestBody SysMenu menu) {
        sysMenuService.updateMenu(menu);
        return ResponseBean.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseBean delete(@RequestBody SysMenu menu) {
        sysMenuService.deleteMenu(menu);
        return ResponseBean.ok();
    }
}
