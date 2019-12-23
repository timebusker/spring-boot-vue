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
        this.currentPage = Integer.parseInt(params.get("currentPage").toString());
        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (currentPage - 1) * pageSize);
        this.put("page", currentPage);
        this.put("limit", pageSize);
    }

    public Query() {
    }
}
