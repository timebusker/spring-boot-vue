package com.timebusker.model;

import freemarker.template.utility.NullArgumentException;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * @DESC:SystemMuen：系统菜单封装类
 * @author:timebusker
 * @date:2019/7/27
 */
public class SystemMenu implements Serializable, Comparable<SystemMenu> {

    private String menuId;
    private String menuPid = "0";
    private String menuName;
    private String menuUrl = "#";
    private String template;
    private boolean isFrame = false;
    private boolean isEnable = true;
    private Set<SystemMenu> children;

    public SystemMenu() {
    }

    public SystemMenu(String menuId, String menuPid, String menuName, boolean isEnable, String menuUrl, String template, boolean isFrame) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    public SystemMenu(String menuId, String menuPid, String menuName, String menuUrl, String template, boolean isFrame) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    public SystemMenu(String menuId, String menuPid, String menuName, boolean isEnable, String template, String menuUrl) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    public SystemMenu(String menuId, String menuPid, String menuName, String menuUrl, String template) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    @Override
    public int compareTo(SystemMenu o) {
        return this.getMenuId().compareTo(o.menuId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        if (StringUtils.isEmpty(menuId)) {
            throw new NullArgumentException("setter方法设置的参数不允许为空或者NULL!");
        }
        this.menuId = menuId;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        if (StringUtils.isEmpty(menuPid)) {
            throw new NullArgumentException("setter方法设置的参数不允许为空或者NULL!");
        }
        this.menuPid = menuPid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        if (StringUtils.isEmpty(menuName)) {
            throw new NullArgumentException("setter方法设置的参数不允许为空或者NULL!");
        }
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        if (StringUtils.isEmpty(menuUrl)) {
            throw new NullArgumentException("setter方法设置的参数不允许为空或者NULL!");
        }
        this.menuUrl = menuUrl;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        if (StringUtils.isEmpty(template)) {
            throw new NullArgumentException("setter方法设置的参数不允许为空或者NULL!");
        }
        this.template = template;
    }

    public boolean isFrame() {
        return isFrame;
    }

    public void setFrame(boolean iframe) {
        isFrame = iframe;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Set<SystemMenu> getChildren() {
        return children;
    }

    public void setChildren(Set<SystemMenu> children) {
        this.children = children;
    }
}
