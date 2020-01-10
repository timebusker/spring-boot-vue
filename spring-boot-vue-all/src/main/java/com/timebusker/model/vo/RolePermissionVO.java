package com.timebusker.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: RolePermissionVO
 * @Author: Administrator
 * @Date: 2020/1/10 13:26
 **/
@Data
public class RolePermissionVO implements Serializable {

    /**
     * 角色信息
     */
    private String symbol;

    /**
     * 资源信息
     */
    private String url;
}
