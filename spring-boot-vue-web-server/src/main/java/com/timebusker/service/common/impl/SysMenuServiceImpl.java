package com.timebusker.service.common.impl;

import com.timebusker.mapper.common.SysMenuMapper;
import com.timebusker.model.common.SysMenu;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.common.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            if (menu.getId() != null) {
                int k = sysMenuMapper.updateByPrimaryKey(menu);
            } else {
                menu.setId(sequenceId.nextId());
                if (menu.getPid() == null) {
                    menu.setPid(0l);
                }
                if (menu.getIsFrame() == null) {
                    menu.setIsFrame(0);
                }
                menu.setCreateTime(new Date());
                sysMenuMapper.insert(menu);
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

    @Override
    public SysMenu getMenuById(long id) {
        SysMenu menu = new SysMenu();
        try {
            menu = sysMenuMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }
}
