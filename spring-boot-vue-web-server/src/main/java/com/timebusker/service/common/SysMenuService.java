package com.timebusker.service.common;

import com.timebusker.model.common.SysMenu;

import java.util.*;

/**
 * @DESC:SysMenuService:系统菜单编辑管理服务类
 * @author:timebusker
 * @date:2019/3/22
 */
public interface SysMenuService {

    List<SysMenu> getMenusList(Map<String, Object> map);

    SysMenu getMenuById(long id);

    boolean deleteMenu(SysMenu menu);

    boolean save(SysMenu menu);
}
