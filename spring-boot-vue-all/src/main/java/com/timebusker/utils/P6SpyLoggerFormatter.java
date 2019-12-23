package com.timebusker.utils;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * @Description: P6SpyLogger
 * @Author: Administrator
 * @Date: 2019/12/21 15:56
 **/
public class P6SpyLoggerFormatter implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return "执行时间：" + now + "\t|\t" + "执行SQL：" + sql;
    }
}
