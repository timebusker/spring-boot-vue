package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.MenuEntity;
import com.timebusker.service.MenuService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MenuEntity> list = menuService.query(query);
        return ResultVO.ok().put("menus", list);
    }
}
