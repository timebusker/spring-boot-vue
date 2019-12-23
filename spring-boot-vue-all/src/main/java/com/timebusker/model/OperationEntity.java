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

/**
 * @Description: OperationEntity
 * @Author: Administrator
 * @Date: 2019/12/19 20:38
 **/
@Data
@ToString
@Entity
@Proxy(lazy = false)
@Table(name = "tb_operation_log")
public class OperationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String userId;
    private String menu;
    private String type;
    private String clazz;
    private String url;
    private String data;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime createTime;
}
