package com.timebusker.test;

import com.alibaba.fastjson.JSON;
import com.timebusker.model.SystemEntity;
import com.timebusker.repository.SystemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Description: SystemRepositoryTest
 * @Author: Administrator
 * @Date: 2019/12/20 20:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemRepositoryTest {

    @Autowired
    private SystemRepository repository;

    @Test
    public void test1() {
        SystemEntity system = new SystemEntity();
        system.setDisabled(false);
        system.setIcon("el-icon-s-help");
        system.setName("权限管理系统");
        system.setType("管理系统");
        system.setUrl("http://12.12.12.11/base");
        system.setUpdateTime(LocalDateTime.now());
        repository.save(system);
    }

    @Test
    public void test2() {
        SystemEntity system = repository.getOne("M_1208002494890905600");
        System.err.println(JSON.toJSONString(system));
    }
}
