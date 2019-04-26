package com.timebusker.service.cache;

import com.timebusker.common.redis.key.BasePrefix;

/**
 * @DESC:SysUserKey
 * @author:timebusker
 * @date:2019/4/23
 */

public class SysUserKey extends BasePrefix {

    private static final int DEFAULT_EXPIRE = 60 * 60;

    private SysUserKey(String prefix) {
        super(prefix);
    }

    private SysUserKey(int expire, String prefix) {
        super(expire, prefix);
    }

    public static final SysUserKey USER_ID = new SysUserKey(DEFAULT_EXPIRE, "USER_ID");
    public static final SysUserKey USER_NAME = new SysUserKey(DEFAULT_EXPIRE, "USER_NAME");
    public static final SysUserKey TOKEN = new SysUserKey(DEFAULT_EXPIRE, "USER_TOKEN");
}
