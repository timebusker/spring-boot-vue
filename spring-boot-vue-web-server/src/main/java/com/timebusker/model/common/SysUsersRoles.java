package com.timebusker.model.common;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_users_roles")
public class SysUsersRoles implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
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
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}