package com.timebusker;

import com.alibaba.fastjson.JSON;
import com.timebusker.mapper.SysMenuMapper;
import com.timebusker.model.SysMenu;
import com.timebusker.utils.SequenceIdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.objectweb.asm.tree.ModuleExportNode;
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

    private SequenceIdUtil sequenceId = new SequenceIdUtil(0, 1);

    @Test
    public void menuTest() {
        SysMenu menu = new SysMenu();
        menu.setId(sequenceId.nextId());
        menu.setIsFrame(0);
        menu.setName("SQL监控");
        menu.setComponent("SQL监控");
        menu.setPid(0L);
        menu.setSort(310183468862541824l);
        menu.setIcon("");
        menu.setUrl("/");
        menu.setCreateTime(new Date());
        sysMenuMapper.insert(menu);
    }

    @Test
    public void menuTree() {
        System.err.println(JSON.toJSONString(sysMenuMapper.queryMenuTree(0l,307956714219507712L)));
    }

    @Test
    public void menuUpdate(){
        SysMenu menu = sysMenuMapper.selectByPrimaryKey(307957599674830850L);
        System.err.println(JSON.toJSONString(menu));
        menu.setIcon("icon-xitonghuancun1");
        sysMenuMapper.updateByPrimaryKey(menu);
        System.err.println(JSON.toJSONString(menu));
    }
}
