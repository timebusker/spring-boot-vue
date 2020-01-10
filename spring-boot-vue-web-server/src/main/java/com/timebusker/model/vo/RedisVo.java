package com.timebusker.model.vo;

import java.io.Serializable;

/**
 * @DESC:RedisVo
 * @author:timebusker
 * @date:2019/4/30
 */
public class RedisVo implements Serializable {

    public RedisVo(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
