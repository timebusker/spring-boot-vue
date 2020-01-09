package com.timebusker.test;

import com.timebusker.model.RoleEntity;
import com.timebusker.model.UserEntity;
import com.timebusker.repository.RoleRepository;
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
    private RoleRepository repository;

    @Test
    public void test1() {
        RoleEntity role = new RoleEntity();
        role.setIcon("el-icon-s-custom");
        role.setDescription("超级管理员权限");
        role.setName("超级管理员");
        role.setStatus(true);
        role.setUpdateTime(LocalDateTime.now());
        repository.save(role);
    }
}
