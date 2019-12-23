package com.timebusker.service;

import com.timebusker.model.MenuPointEntity;
import com.timebusker.repository.MenuPointRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: MenuPointService
 * @Author: Administrator
 * @Date: 2019/12/20 14:22
 **/
@Service
public class MenuPointService extends AbstractBaseServiceImpl<MenuPointEntity, MenuPointRepository> {

    @Autowired
    public MenuPointRepository menuPointRepository;

    @Override
    protected void instance() {
        this.setRepository(menuPointRepository);
    }

    @Override
    public MenuPointEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<MenuPointEntity> query(Query params) {
        return null;
    }

    @Override
    public Page<MenuPointEntity> query(Query params, Pageable pageable) {
        return null;
    }
}