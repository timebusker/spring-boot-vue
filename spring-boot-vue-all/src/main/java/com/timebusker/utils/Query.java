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

    private int currentPage;
    private int totalPage;
    private int pageSize;
    private int totalCount;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        //分页参数
        this.currentPage = isBlankValue("currentPage") ? 0 : Integer.parseInt(params.get("currentPage").toString());
        this.pageSize = isBlankValue("pageSize") ? 15 : Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (currentPage - 1) * pageSize);
        this.put("page", currentPage);
        this.put("limit", pageSize);
    }

    public Query() {
    }


    public boolean isBlankValue(String key) {
        if (this.get(key) == null) {
            return true;
        } else {
            return this.get(key).toString().trim().equals("");
        }
    }
}
