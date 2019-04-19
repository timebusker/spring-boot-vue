package com.timebusker.model.file;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "file_image")
public class FileImage implements Serializable {

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 所有者
     */
    @Column(name = "user_id")
    private String userId;

    private String url;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 上传时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取文件名称
     *
     * @return name - 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名称
     *
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取所有者
     *
     * @return user_id - 所有者
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置所有者
     *
     * @param userId 所有者
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取文件描述
     *
     * @return description - 文件描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文件描述
     *
     * @param description 文件描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取上传时间
     *
     * @return create_time - 上传时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置上传时间
     *
     * @param createTime 上传时间
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
        sb.append(", name=").append(name);
        sb.append(", userId=").append(userId);
        sb.append(", url=").append(url);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}