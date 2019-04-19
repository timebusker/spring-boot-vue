package com.timebusker.common.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @DESC:ResponBean:返回结果封装bean
 * @author:timebusker
 * @date:2019/3/14
 */
public class ResultVo extends HashMap<String, Object> implements Serializable {

    public ResultVo() {
        put("code", 0);
    }

    public static ResultVo error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员！");
    }

    public static ResultVo error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResultVo error(int code, String msg) {
        ResultVo r = new ResultVo();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultVo ok(String msg) {
        ResultVo r = new ResultVo();
        r.put("msg", msg);
        return r;
    }

    public static ResultVo ok(Map<String, Object> map) {
        ResultVo r = new ResultVo();
        r.putAll(map);
        return r;
    }

    public static ResultVo ok() {
        return new ResultVo();
    }

    public ResultVo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
