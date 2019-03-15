package com.timebusker.common.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @DESC:ResponBean:返回结果封装bean
 * @author:timebusker
 * @date:2019/3/14
 */
public class ResponseBean extends HashMap<String, Object> implements Serializable {

    public ResponseBean() {
        put("code", 0);
    }

    public static ResponseBean error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员！");
    }

    public static ResponseBean error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResponseBean error(int code, String msg) {
        ResponseBean r = new ResponseBean();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResponseBean ok(String msg) {
        ResponseBean r = new ResponseBean();
        r.put("msg", msg);
        return r;
    }

    public static ResponseBean ok(Map<String, Object> map) {
        ResponseBean r = new ResponseBean();
        r.putAll(map);
        return r;
    }

    public static ResponseBean ok() {
        return new ResponseBean();
    }

    public ResponseBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
