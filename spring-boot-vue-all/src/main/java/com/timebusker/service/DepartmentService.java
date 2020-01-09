package com.timebusker.service;

import com.timebusker.model.DepartmentEntity;
import com.timebusker.repository.DepartmentRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: DepartmentService
 * @Author: Administrator
 * @Date: 2019/12/20 14:16
 **/
@Service
public class DepartmentService extends AbstractBaseServiceImpl<DepartmentEntity, DepartmentRepository> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    protected void instance() {
        this.setRepository(departmentRepository);
    }

}
