package com.timebusker;

import com.timebusker.mapper.SysMenuMapper;
import com.timebusker.model.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @DESC:ApplicationTest:单元测试类
 * @author:timebusker
 * @date:2019/3/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void menuTest() {
        SysMenu menu = new SysMenu();
        menu.setComponent("11111");
        menu.setCreateTime(new Date());
        menu.setIcon("111111");
        menu.setId(2L);
        menu.setIsFrame(0);
        menu.setPid(0l);
        menu.setSort(1L);
        menu.setName("测试的");
        sysMenuMapper.insert(menu);

        SysMenu menu1 = sysMenuMapper.selectByPrimaryKey(2);
        System.out.println(menu1);
    }
}
