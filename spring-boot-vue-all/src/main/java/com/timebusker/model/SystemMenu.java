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
    private String icon;
    private String menuName;
    private String menuUrl = "#";
    private String template;
    private String frameType = "";
    private boolean disabled = false;
    private Set<SystemMenu> children;

    public SystemMenu() {
    }

    public SystemMenu(String menuId, String menuPid, String icon, String menuName, String menuUrl, String template, String frameType, boolean disabled) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.icon = icon;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.template = template;
        this.frameType = frameType;
        this.disabled = disabled;
    }

    public SystemMenu(String menuId, String menuPid, String icon, String menuName, String menuUrl, String template, String frameType) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.icon = icon;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.template = template;
        this.frameType = frameType;
    }

    public SystemMenu(String menuId, String menuPid, String icon, String menuName, String menuUrl, String template, boolean disabled) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.icon = icon;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.template = template;
        this.disabled = disabled;
    }

    public SystemMenu(String menuId, String menuPid, String icon, String menuName, String menuUrl, String template) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.icon = icon;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.template = template;
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

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Set<SystemMenu> getChildren() {
        return children;
    }

    public void setChildren(Set<SystemMenu> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
