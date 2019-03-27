package com.timebusker.service;

import com.timebusker.mapper.SysMenuMapper;
import com.timebusker.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @DESC:SysMenuService:系统菜单编辑管理服务类
 * @author:timebusker
 * @date:2019/3/22
 */
public interface SysMenuService {

    List<SysMenu> getMenusList(Map<String, Object> map);

    boolean deleteMenu(SysMenu menu);

    boolean save(SysMenu menu);
}
