package com.timebusker.common.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @DESC:HttpMessageConverter
 * @author:timebusker
 * @date:2019/3/28
 */
public class HttpLongMapper extends ObjectMapper {

    public HttpLongMapper() {
        super();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);
    }
}
