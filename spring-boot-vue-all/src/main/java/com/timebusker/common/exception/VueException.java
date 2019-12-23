package com.timebusker.common.exception;

/**
 * @Description: VueException-统一异常信息类
 * @Author: Administrator
 * @Date: 2019/12/19 23:11
 **/
public class VueException extends RuntimeException {

    private String msg;
    private int code = 500;

    public VueException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public VueException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public VueException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public VueException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
