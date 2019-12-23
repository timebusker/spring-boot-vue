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
 * @Description:SystemDepartment
 * @Author:Administrator
 * @Date2019/12/19 16:40
 **/
@Data
@ToString
@Entity
@Proxy(lazy = false)
@Table(name = "tb_department")
public class DepartmentEntity implements Serializable, Comparable<DepartmentEntity> {

    /**
     * @GeneratedValue注解的strategy属性提供四种值： – AUTO： 主键由程序控制，是默认选项，不设置即此项。
     * <p>
     * – IDENTITY：主键由数据库自动生成，即采用数据库ID自增长的方式，Oracle不支持这种方式。
     * <p>
     * – SEQUENCE：通过数据库的序列产生主键，通过@SequenceGenerator 注解指定序列名，mysql不支持这种方式。
     * <p>
     * – TABLE：通过特定的数据库表产生主键，使用该策略可以使应用更易于数据库移植。
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String parentId;
    private String name;
    private String icon;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime updateTime;
    private String updateUserId;
    private String leaderUserId;

    @Transient
    private List<DepartmentEntity> children;

    @Override
    public int compareTo(DepartmentEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
