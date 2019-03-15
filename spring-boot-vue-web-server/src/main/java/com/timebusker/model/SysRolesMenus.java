package com.timebusker.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_roles_menus")
public class SysRolesMenus implements Serializable {
    /**
     * 菜单id
     */
    @Id
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}