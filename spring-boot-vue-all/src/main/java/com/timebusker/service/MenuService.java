package com.timebusker.service;

import com.timebusker.common.SystemConstant;
import com.timebusker.model.MenuEntity;
import com.timebusker.repository.MenuRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: MenuService
 * @Author: Administrator
 * @Date: 2019/12/20 14:17
 **/
@Service
public class MenuService extends AbstractBaseServiceImpl<MenuEntity, MenuRepository> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected void instance() {
        this.setRepository(menuRepository);
    }

    @Override
    public MenuEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<MenuEntity> query(Query params) {
        String systemId = params.isBlankValue("systemId") ? SystemConstant.DEFAULT_SYSTEM_ID : params.get("systemId").toString();
        String parentId = params.isBlankValue("parentId") ? SystemConstant.DEFAULT_MENU_PARENT_ID : params.get("parentId").toString();
        List<MenuEntity> list = menuRepository.queryBySystemIdAndParentId(systemId, parentId);
        for (MenuEntity menu : list) {
            parentId = menu.getId();
            List<MenuEntity> children = menuRepository.queryBySystemIdAndParentId(systemId, parentId);
            if (children != null) {
                menu.setChildren(children);
            }
        }
        return list;
    }

    @Override
    public Page<MenuEntity> query(Query params, Pageable pageable) {
        return null;
    }
}
