package com.timebusker.exceptions;

/**
 * @DESC:VueException:自定义异常封装类
 * @author:timebusker
 * @date:2019/3/13
 */
public class CommonException extends RuntimeException {


    private String msg;
    private int code = 500;

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CommonException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public CommonException(String message, String msg, int code) {
        super(message);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(String message, Throwable cause, String msg, int code) {
        super(message, cause);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(Throwable cause, String msg, int code) {
        super(cause);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
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
