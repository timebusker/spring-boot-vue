package com.timebusker.test;

import com.alibaba.fastjson.JSON;
import com.timebusker.model.RoleEntity;
import com.timebusker.model.UserEntity;
import com.timebusker.repository.RoleRepository;
import com.timebusker.repository.RoleResourceRepository;
import com.timebusker.repository.UserRepository;
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
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Test
    public void test1() {
        RoleEntity role = new RoleEntity();
        role.setIcon("el-icon-s-custom");
        role.setDescription("超级管理员权限");
        role.setName("超级管理员");
        role.setStatus(true);
        role.setUpdateTime(LocalDateTime.now());
        roleRepository.save(role);
    }

    @Test
    public void test2() {
        System.err.println(JSON.toJSONString(roleResourceRepository.findAllPermission().size()));
        System.err.println(JSON.toJSONString(roleResourceRepository.findAllPermission()));
    }

    @Test
    public void test3() {
        System.err.println(JSON.toJSONString(roleRepository.queryByUserId("M_1214168112337915904")));
    }
}