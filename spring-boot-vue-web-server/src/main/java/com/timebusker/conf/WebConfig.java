package com.timebusker.conf;

import com.timebusker.common.converter.HttpLongMapper;
import com.timebusker.common.web.UserArgumentResolver;
import com.timebusker.utils.DateUtil;
import com.timebusker.utils.SequenceIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @DESC:WebConfig
 * @author:timebusker
 * @date:2019/4/25
 */

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Autowired
    UserArgumentResolver userArgumentResolver;

    /**
     * 注册参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }


    /**
     * 对于Long 类型的数据，如果我们在Controller层通过@ResponseBody将返回数据自动转换成json时，不做任何处理，而直接传给前端的话，在Long长度大于17位时会出现精度丢失的问题。
     * <p>
     * 首先，我们分析一下@ResponseBody是怎样将一个普通的对象转换成Json对象返回:
     *
     * @return
     * @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器（默认使用MappingJackson2HttpMessageConverte （Spring 4.x以下使用的是MappingJackson2HttpMessageConverte））
     * 转换为指定的格式之后，写入到response对象的body区，需要注意的是，在使用此注解之后不会再走视图处理器，
     * 而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。作用等同于response.getWriter.write(JSONObject.fromObject(user).toString());
     * <p>
     * 总的来说主要有两种处理方式，如何避免精度丢失呢？
     * <p>
     * 1、最常用的办法就是待转化的字段统一转成String类型--->需要针对每个属性进行设置（@JsonSerialize(using=ToStringSerializer.class)），属性太多时，麻烦
     * 2、配置spring的消息转换器的ObjectMapper为自定义的类--->本项目采用的方法
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 设置JSON格式化时间格式
        HttpLongMapper mapper = new HttpLongMapper();
        DateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_DATE_PATTERN2);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.setDateFormat(format);
        // 注入转换器
        converter.setObjectMapper(mapper);
        return converter;
    }

    /**
     * 统一注入单例SequenceIdUtil
     *
     * @return
     */
    @Bean
    public SequenceIdUtil sequenceIdUtil() {
        SequenceIdUtil util = new SequenceIdUtil(0, 1);
        return util;
    }
}
