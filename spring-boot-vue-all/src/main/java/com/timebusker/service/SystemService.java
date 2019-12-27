package com.timebusker.service;

import com.timebusker.model.SystemEntity;
import com.timebusker.repository.SystemRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SystemService
 * @Author: Administrator
 * @Date: 2019/12/20 14:24
 **/
@Service
public class SystemService extends AbstractBaseServiceImpl<SystemEntity, SystemRepository> {

    @Autowired
    private SystemRepository systemRepository;

    @Override
    protected void instance() {
        this.setRepository(systemRepository);
    }

    @Override
    public SystemEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<SystemEntity> query(Query params) {
        List<SystemEntity> list = new ArrayList<>();
        if (params == null || params.isEmpty()) {
            list = systemRepository.findAll(systemRepository.sort);
        }
        return list;
    }

    @Override
    public Page<SystemEntity> query(Query params, Pageable pageable) {
        return null;
    }
}
