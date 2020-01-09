package com.timebusker.service;

import com.timebusker.model.SystemConfigEntity;
import com.timebusker.repository.SystemConfigRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: SystemConfigService
 * @Author: Administrator
 * @Date: 2020/1/6 20:06
 **/
@Service
public class SystemConfigService extends AbstractBaseServiceImpl<SystemConfigEntity, SystemConfigRepository> {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Override
    protected void instance() {
        this.setRepository(systemConfigRepository);
    }
}
