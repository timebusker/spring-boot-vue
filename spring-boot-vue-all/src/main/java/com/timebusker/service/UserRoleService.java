package com.timebusker.service;

import com.timebusker.model.UserRoleEntity;
import com.timebusker.repository.UserRoleRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserRoleService
 * @Author: Administrator
 * @Date: 2019/12/20 14:26
 **/
@Service
public class UserRoleService extends AbstractBaseServiceImpl<UserRoleEntity, UserRoleRepository> {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    protected void instance() {
        this.setRepository(userRoleRepository);
    }
}
