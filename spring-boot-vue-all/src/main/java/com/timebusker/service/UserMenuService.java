package com.timebusker.service;

import com.timebusker.model.UserMenuEntity;
import com.timebusker.repository.UserMenuRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserMenuService
 * @Author: Administrator
 * @Date: 2019/12/20 14:25
 **/
@Service
public class UserMenuService extends AbstractBaseServiceImpl<UserMenuEntity, UserMenuRepository> {

    @Autowired
    public UserMenuRepository userMenuRepository;

    @Override
    protected void instance() {
        this.setRepository(userMenuRepository);
    }

    @Override
    public UserMenuEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<UserMenuEntity> query(Query params) {
        return null;
    }

    @Override
    public Page<UserMenuEntity> query(Query params, Pageable pageable) {
        return null;
    }
}
