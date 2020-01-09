package com.timebusker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.timebusker.utils.date.DateUtil;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description:SystemRole
 * @Author:Administrator
 * @Date2019/12/19 16:36
 **/
@Data
@ToString
@Entity
@Table(name = "tb_role")
public class RoleEntity implements Serializable, Comparable<RoleEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String name;
    private String symbol;
    private String icon;
    private boolean status;
    private String description;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime updateTime;

    @Override
    public int compareTo(RoleEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
