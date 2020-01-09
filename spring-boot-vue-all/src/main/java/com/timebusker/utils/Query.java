package com.timebusker.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Query
 * @Author: Administrator
 * @Date: 2019/12/20 13:33
 **/
@Data
public class Query extends HashMap<String, Object> {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_CURRENT_PAGE = 0;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private int totalCount;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        //分页参数
        this.currentPage = isBlankValue("currentPage") ? DEFAULT_CURRENT_PAGE : Integer.parseInt(params.get("currentPage").toString()) - 1;
        this.pageSize = isBlankValue("pageSize") ? DEFAULT_PAGE_SIZE : Integer.parseInt(params.get("pageSize").toString());
    }

    public Query() {
    }


    public boolean isBlankValue(String key) {
        if (this.get(key) == null) {
            return true;
        } else {
            boolean flag = this.get(key).toString().trim().equals("") || this.get(key).toString().trim().equals("0");
            return flag;
        }
    }
}
