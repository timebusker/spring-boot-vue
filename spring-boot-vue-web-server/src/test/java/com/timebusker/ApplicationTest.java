package com.timebusker;

import com.alibaba.fastjson.JSON;
import com.timebusker.mapper.common.SysMenuMapper;
import com.timebusker.model.common.SysMenu;
import com.timebusker.utils.SequenceIdUtil;
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

    @Autowired
    private SequenceIdUtil sequenceId;

    @Test
    public void menuTest() {
        SysMenu menu = new SysMenu();
        menu.setId(sequenceId.nextId());
        menu.setIsFrame(0);
        menu.setName("SQL监控");
        menu.setComponentPath("/home");
        menu.setPid(0L);
        menu.setSort(310183468862541824l);
        menu.setIcon("");
        menu.setPath("/");
        menu.setCreateTime(new Date());
        sysMenuMapper.insert(menu);
    }

    @Test
    public void menuTree() {
        System.err.println(JSON.toJSONString(sysMenuMapper.queryMenuTree(0l, 307956714219507712L)));
    }

    @Test
    public void menuUpdate() {
        SysMenu menu = sysMenuMapper.selectByPrimaryKey(307957599674830850L);
        System.err.println(JSON.toJSONString(menu));
        menu.setIcon("icon-xitonghuancun1");
        sysMenuMapper.updateByPrimaryKey(menu);
        System.err.println(JSON.toJSONString(menu));
    }

    public static void main(String[] args) {
        TestMapObject object = new TestMapObject();
        object.setId("AAAAAAAAAAAAAAA");
        object.setName("CCCCCCCCCCCCCC");
        System.err.println("\t\t\t" + object);
        object.test(object);
        System.err.println("\t\t\t" + object);
    }
}
