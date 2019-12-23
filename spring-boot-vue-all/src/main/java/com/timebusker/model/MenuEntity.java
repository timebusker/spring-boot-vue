package com.timebusker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.timebusker.utils.date.DateUtil;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @DESC:SystemMuen：系统菜单封装类
 * @author:timebusker
 * @date:2019/7/27
 */
@Data
@ToString
@Entity
@Proxy(lazy = false)
@Table(name = "tb_menu")
public class MenuEntity implements Serializable, Comparable<MenuEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String systemId;
    private String parentId;
    private String icon;
    private String name;
    private String url = "#";
    private String template;
    private String type = "";
    private boolean disabled = false;
    private int sort;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime updateTime;
    private String updateUserId;

    @Transient
    private List<MenuEntity> children;

    @Override
    public int compareTo(MenuEntity o) {
        return this.getSort() - o.getSort();
    }
}
