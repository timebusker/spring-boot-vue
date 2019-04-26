package com.timebusker;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.redis.RedisService;
import com.timebusker.mapper.common.SysMenuMapper;
import com.timebusker.mapper.common.SysUserMapper;
import com.timebusker.model.common.SysMenu;
import com.timebusker.model.common.SysUser;
import com.timebusker.service.cache.SysUserKey;
import com.timebusker.utils.MD5Utils;
import com.timebusker.utils.SequenceIdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

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
    private SysUserMapper sysUserMapper;

    @Autowired
    private SequenceIdUtil sequenceId;

    @Autowired
    private RedisService redisService;

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


    @Test
    public void testExample() {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", "admin");
        criteria.andEqualTo("password", MD5Utils.MD5Encode(123));
        SysUser user = sysUserMapper.selectOneByExample(example);
        System.err.println(JSON.toJSONString(user));
    }

    @Test
    public void testRedis(){
        redisService.set(SysUserKey.TOKEN, "111111111111", new SysUser("1111",0,"222","333"));
        SysUser user = redisService.get(SysUserKey.TOKEN, "111111111111", SysUser.class);
        System.err.println(user);
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
