package com.timebusker.mapper;

import com.timebusker.common.mybatis.CommonMapper;
import com.timebusker.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends CommonMapper<SysMenu> {

    List<SysMenu> queryMenuTree(@Param("id") long id, @Param("pid") long pid);
}