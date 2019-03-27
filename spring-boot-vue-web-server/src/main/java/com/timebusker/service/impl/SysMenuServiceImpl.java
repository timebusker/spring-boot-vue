package com.timebusker.service.impl;

import com.timebusker.mapper.SysMenuMapper;
import com.timebusker.model.SysMenu;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @DESC:SysMenuServiceImpl
 * @author:timebusker
 * @date:2019/3/22
 */
@Service
public class SysMenuServiceImpl extends AbstractBaseService implements SysMenuService {

    @Autowired
    public SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenusList(Map<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        List<SysMenu> list = new ArrayList<>();
        try {
            list = sysMenuMapper.queryMenuTree(0l, 0l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(SysMenu menu) {
        try {
            if (menu.getId() <= 0) {
                sysMenuMapper.insert(menu);
            } else {
                sysMenuMapper.updateByPrimaryKey(menu);
            }
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
}
