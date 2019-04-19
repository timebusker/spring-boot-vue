package com.timebusker.model.common;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_roles_permissions")
public class SysRolesPermissions implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id")
    private Long permissionId;

    private static final long serialVersionUID = 1L;

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

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append("]");
        return sb.toString();
    }
}