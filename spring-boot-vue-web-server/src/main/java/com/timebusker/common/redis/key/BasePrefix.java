package com.timebusker.common.redis.key;

public abstract class BasePrefix implements KeyPrefix {

    private static final int DEFAULT_EXPIRE = 60 * 30;

    private int expire;

    private String prefix;

    /**
     * 0:代表永不过期
     *
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(DEFAULT_EXPIRE, prefix);
    }

    public BasePrefix(int expire, String prefix) {
        this.expire = expire;
        this.prefix = prefix;
    }

    public int getExpire() {
        return expire;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + "#" + prefix + "#";
    }

}
