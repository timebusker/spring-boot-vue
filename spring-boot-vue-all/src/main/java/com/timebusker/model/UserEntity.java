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
 * @Description:SystemUser
 * @Author:Administrator
 * @Date2019/12/19 16:36
 **/
@Data
@ToString
@Entity
@Table(name = "tb_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String departId;
    private String icon;
    private String type;
    private String nickName;
    private String loginName;
    private String telNumber;
    private String address;
    private boolean disabled = false;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime updateTime;
}
