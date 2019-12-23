package com.timebusker.utils;

import com.timebusker.common.exception.VueException;
import com.timebusker.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @Description: BeanInvokeUtil
 * @Author: Administrator
 * @Date: 2019/12/21 15:13
 **/
public class BeanInvokeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME);

    public static void invoke(Object instance, HashMap<String, Object> params) {
        try {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 把对象上的name字段设置为public访问属性
                field.setAccessible(true);
                String name = field.getName();
                if (params.containsKey(name)) {
                    String value = params.get(name).toString();
                    if (field.getType().getName().equals("java.math.BigDecimal")) {
                        if (StringUtils.isNotBlank(value)) {
                            BigDecimal column = new BigDecimal(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("java.lang.Integer")) {
                        if (StringUtils.isNotBlank(value)) {
                            Integer column = new Integer(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("java.lang.Long")) {
                        if (StringUtils.isNotBlank(value)) {
                            Long column = new Long(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("java.lang.Double")) {
                        if (StringUtils.isNotBlank(value)) {
                            Double column = new Double(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("java.time.LocalDateTime")) {
                        if (StringUtils.isNotBlank(value)) {
                            LocalDateTime column = LocalDateTime.parse(value, formatter);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("int")) {
                        if (StringUtils.isNotBlank(value)) {
                            int column = Integer.parseInt(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("long")) {
                        if (StringUtils.isNotBlank(value)) {
                            long column = Long.parseLong(value);
                            field.set(instance, column);
                        }
                    } else if (field.getType().getName().equals("boolean")) {
                        if (StringUtils.isNotBlank(value)) {
                            boolean column = Boolean.parseBoolean(value);
                            field.set(instance, column);
                        }
                    } else {
                        field.set(instance, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new VueException("对象反射属性绑定失败！");
        }
    }
}
