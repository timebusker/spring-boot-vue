package com.timebusker.model.file;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "file_tmp")
public class FileTmp implements Serializable {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 文件URL地址
     */
    private String url;

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
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取文件URL地址
     *
     * @return url - 文件URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置文件URL地址
     *
     * @param url 文件URL地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        sb.append(", url=").append(url);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}