package com.timebusker.service.impl;

import com.timebusker.mapper.SysMenuMapper;
import com.timebusker.model.SysMenu;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @DESC:SysMenuServiceImpl
 * @author:timebusker
 * @date:2019/3/22
 */
public class SysMenuServiceImpl extends AbstractBaseService implements SysMenuService {

    @Autowired
    public SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenusList(Map<String, Object> map) {
        List<SysMenu> list = new ArrayList<>();
        try {
            list = sysMenuMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean AddMenu(SysMenu menu) {
        try {
            sysMenuMapper.insert(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean deleteMenu(SysMenu menu) {
        try {
            sysMenuMapper.delete(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateMenu(SysMenu menu) {
        try {
            sysMenuMapper.updateByPrimaryKey(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }
}
