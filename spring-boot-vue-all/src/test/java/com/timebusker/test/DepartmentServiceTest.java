package com.timebusker.test;

import com.timebusker.model.DepartmentEntity;
import com.timebusker.service.DepartmentService;
import com.timebusker.utils.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @Description: DepartmentServiceTest
 * @Author: Administrator
 * @Date: 2019/12/21 17:22
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void test1() {
        DepartmentEntity department = new DepartmentEntity();
        department.setIcon("----");
        department.setLeaderUserId("time-busker");
        department.setName("时创科技有限有限责任公司");
        department.setUpdateTime(LocalDateTime.now());
        department.setUpdateUserId("time-busker");
        departmentService.save(department);
    }

    @Test
    public void test2() {
        Query query = new Query();
        query.put("id", "M_1208321869544034304");
        query.put("parentId", "100001");
        query.put("icon", "@@@@");
        departmentService.updateByParams(query);
    }
}
