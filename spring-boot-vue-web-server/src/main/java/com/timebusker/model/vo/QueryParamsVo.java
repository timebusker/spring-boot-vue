package com.timebusker.model.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * @DESC:QueryParamsVo
 * @author:timebusker
 * @date:2019/4/17
 */
public class QueryParamsVo implements Serializable {

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize = 10;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage = 1;

    private Map<String,Object> conditions;

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
}
