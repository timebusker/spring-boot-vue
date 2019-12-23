package com.timebusker.service;

import com.timebusker.model.DepartmentEntity;
import com.timebusker.repository.DepartmentRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Override
    public DepartmentEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<DepartmentEntity> query(Query params) {
        return null;
    }

    @Override
    public Page<DepartmentEntity> query(Query params, Pageable pageable) {
        return null;
    }
}
