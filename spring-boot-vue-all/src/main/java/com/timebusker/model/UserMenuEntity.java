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
 * @Description: UserMenuEntity
 * @Author: Administrator
 * @Date: 2019/12/19 20:01
 **/
@Data
@ToString
@Entity
@Table(name = "tb_user_menu")
public class UserMenuEntity implements Serializable {

    @EmbeddedId
    private UserMenuKey idx;

    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime createTime;

    @Embeddable
    @ToString
    @Data
    class UserMenuKey implements Serializable {

        private String userId;

        private String menuId;
    }
}
