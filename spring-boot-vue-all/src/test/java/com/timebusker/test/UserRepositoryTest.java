package com.timebusker.test;

import com.alibaba.fastjson.JSON;
import com.timebusker.model.SystemEntity;
import com.timebusker.model.UserEntity;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void test1() {
        UserEntity user = new UserEntity();
        user.setAddress("昆明市盘龙区北京路1001号");
        user.setDepartId("云南研发中心");
        user.setDisabled(false);
        user.setIcon("el-icon-user");
        user.setLoginName("timebusker");
        user.setNickName("timebsuker");
        user.setTelNumber("13899998888");
        user.setType("管理员");
        user.setPassword("123");
        user.setUpdateTime(LocalDateTime.now());
        repository.save(user);
    }
}
