package com.timebusker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.timebusker.utils.date.DateUtil;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Description: UserRoleEntity
 * @Author: Administrator
 * @Date: 2019/12/19 20:01
 **/
@Data
@ToString
@Entity
@Table(name = "tb_user_role")
public class UserRoleEntity implements Serializable {

    /**
     * 同一个类中不能同时使用多个@Id做复合主键
     * <p>
     * 可采用@Embeddable和@EmbeddedId
     */

    @EmbeddedId
    private UserRoleKey idx;

    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime createTime;

    @Embeddable
    @ToString
    @Data
    class UserRoleKey implements Serializable {

        private String userId;

        private String roleId;
    }
}
