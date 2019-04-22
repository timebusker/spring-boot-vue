package com.timebusker.model.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 是否外链
     */
    @Column(name = "is_frame")
    private Integer isFrame;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 组件
     */
    @Column(name = "component")
    private String componentPath;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接地址
     */
    @Column(name="url")
    private String path;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 忽略该属性在JPA中映射
     */
    @Transient
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取是否外链
     *
     * @return is_frame - 是否外链
     */
    public Integer getIsFrame() {
        return isFrame;
    }

    /**
     * 设置是否外链
     *
     * @param isFrame 是否外链
     */
    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取组件
     *
     * @return component - 组件
     */
    public String getComponentPath() {
        return componentPath;
    }

    /**
     * 设置组件
     *
     * @param component 组件
     */
    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath == null ? null : componentPath.trim();
    }

    /**
     * 获取上级菜单ID
     *
     * @return pid - 上级菜单ID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置上级菜单ID
     *
     * @param pid 上级菜单ID
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取链接地址
     *
     * @return url - 链接地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置链接地址
     *
     * @param url 链接地址
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isFrame=").append(isFrame);
        sb.append(", name=").append(name);
        sb.append(", componentPath=").append(componentPath);
        sb.append(", pid=").append(pid);
        sb.append(", sort=").append(sort);
        sb.append(", icon=").append(icon);
        sb.append(", url=").append(path);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}