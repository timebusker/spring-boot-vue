package com.timebusker.model.common;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    /**
     * id主键
     */
    @Id
    private Long id;

    /**
     * 别名
     */
    private String alias;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级id主键
     */
    private Long pid;

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
     * 获取别名
     *
     * @return alias - 别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置别名
     *
     * @param alias 别名
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取上级id主键
     *
     * @return pid - 上级id主键
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置上级id主键
     *
     * @param pid 上级id主键
     */
    public void setPid(Long pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alias=").append(alias);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}