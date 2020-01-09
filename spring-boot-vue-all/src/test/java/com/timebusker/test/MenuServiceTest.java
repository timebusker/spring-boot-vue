package com.timebusker.test;

import com.timebusker.model.MenuEntity;
import com.timebusker.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: MenuServiceTest
 * @Author: Administrator
 * @Date: 2019/12/22 12:46
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void test3() {
        MenuEntity menu = new MenuEntity();
        menu.setDisabled(false);
        menu.setIcon("###");
        menu.setName("UI站点");
        menu.setParentId("0");
        menu.setSort(5);
        menu.setSystemId("M_1208608669512634368");
        menu.setTemplate("");
        menu.setType("外部链接");
        menu.setUpdateTime(LocalDateTime.now());
        menu.setUrl("https://element.eleme.cn/#/zh-CN");
        menu.setUpdateUserId("admin");
        menuService.save(menu);
    }

    @Test
    public void test2() {
        MenuEntity menu1 = new MenuEntity();
        menu1.setDisabled(false);
        menu1.setIcon("el-icon-eleme");
        menu1.setName("系统配置");
        menu1.setParentId("M_1208626369043501058");
        menu1.setSort(2);
        menu1.setSystemId("M_1208608669512634368");
        menu1.setTemplate("configView");
        menu1.setType("子菜单");
        menu1.setUpdateTime(LocalDateTime.now());
        menu1.setUrl("/config/list");
        menu1.setUpdateUserId("admin");
        menuService.save(menu1);
    }

    @Test
    public void test1() {
        Set<MenuEntity> set = new HashSet<>();
        MenuEntity menu1 = new MenuEntity();
        menu1.setDisabled(false);
        menu1.setIcon("###");
        menu1.setName("系统管理");
        menu1.setParentId("0");
        menu1.setSort(1);
        menu1.setSystemId("M_1208608669512634368");
        menu1.setTemplate("");
        menu1.setType("父菜单");
        menu1.setUpdateTime(LocalDateTime.now());
        menu1.setUrl("/");
        menu1.setUpdateUserId("admin");

        MenuEntity menu2 = new MenuEntity();
        menu2.setDisabled(false);
        menu2.setIcon("###");
        menu2.setName("权限管理");
        menu2.setParentId("0");
        menu2.setSort(2);
        menu2.setSystemId("M_1208608669512634368");
        menu2.setTemplate("");
        menu2.setType("父菜单");
        menu2.setUpdateTime(LocalDateTime.now());
        menu2.setUrl("/");
        menu2.setUpdateUserId("admin");

        MenuEntity menu3 = new MenuEntity();
        menu3.setDisabled(false);
        menu3.setIcon("###");
        menu3.setName("系统日志");
        menu3.setParentId("0");
        menu3.setSort(3);
        menu3.setSystemId("M_1208608669512634368");
        menu3.setTemplate("");
        menu3.setType("父菜单");
        menu3.setUpdateTime(LocalDateTime.now());
        menu3.setUrl("/");
        menu3.setUpdateUserId("admin");

        MenuEntity menu4 = new MenuEntity();
        menu4.setDisabled(false);
        menu4.setIcon("###");
        menu4.setName("系统监控");
        menu4.setParentId("0");
        menu4.setSort(4);
        menu4.setSystemId("M_1208608669512634368");
        menu4.setTemplate("");
        menu4.setType("父菜单");
        menu4.setUpdateTime(LocalDateTime.now());
        menu4.setUrl("/");
        menu4.setUpdateUserId("admin");

        set.add(menu1);
        set.add(menu2);
        set.add(menu3);
        set.add(menu4);
        menuService.save(set);
    }
}
