package com.timebusker.service;

import com.timebusker.model.RoleEntity;
import com.timebusker.repository.RoleRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: RoleService
 * @Author: Administrator
 * @Date: 2019/12/20 14:24
 **/
@Service
public class RoleService extends AbstractBaseServiceImpl<RoleEntity, RoleRepository> {

    @Autowired
    public RoleRepository roleRepository;

    @Override
    protected void instance() {
        this.setRepository(roleRepository);
    }
}
