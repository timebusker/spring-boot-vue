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
 * @Description: SystemConfigEntity
 * @Author: Administrator
 * @Date: 2020/1/6 17:18
 **/
@Data
@ToString
@Entity
@Table(name = "tb_system_config")
public class SystemConfigEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.timebusker.utils.IdGeneratorUtil")
    private String id;
    private String configType;
    private String configKey;
    private String configValue;
    private boolean disabled = false;
    private boolean isCovered = false;
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime updateTime;
    private String updateUserId;
}
