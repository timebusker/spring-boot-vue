package com.timebusker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.timebusker.utils.date.DateUtil;
import lombok.AllArgsConstructor;
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
@Table(name = "tb_role_resource")
public class RoleResourceEntity implements Serializable {

    @EmbeddedId
    private RoleResourceEntity.RoleResourceKey idx;

    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime createTime;

    @Embeddable
    @ToString
    @Data
    public static class RoleResourceKey implements Serializable {

        private String roleId;

        private String resourceId;

        public RoleResourceKey() {
        }

        public RoleResourceKey(String roleId, String resourceId) {
            this.roleId = roleId;
            this.resourceId = resourceId;
        }
    }
}
