package com.timebusker.model.common;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * id主键
     */
    @Id
    private Long id;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 账户状态：1启用、0禁用
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户/登录名
     */
    private String username;

    /**
     * 最后修改密码的日期
     */
    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id主键
     *
     * @return id - id主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id主键
     *
     * @param id id主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取邮件地址
     *
     * @return email - 邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮件地址
     *
     * @param email 邮件地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取账户状态：1启用、0禁用
     *
     * @return status - 账户状态：1启用、0禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账户状态：1启用、0禁用
     *
     * @param status 账户状态：1启用、0禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户/登录名
     *
     * @return username - 用户/登录名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户/登录名
     *
     * @param username 用户/登录名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取最后修改密码的日期
     *
     * @return last_password_reset_time - 最后修改密码的日期
     */
    public Date getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    /**
     * 设置最后修改密码的日期
     *
     * @param lastPasswordResetTime 最后修改密码的日期
     */
    public void setLastPasswordResetTime(Date lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SysUser(String email, Integer status, String password, String username) {
        this.email = email;
        this.status = status;
        this.password = password;
        this.username = username;
    }

    public SysUser() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", password=").append(password);
        sb.append(", username=").append(username);
        sb.append(", lastPasswordResetTime=").append(lastPasswordResetTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}