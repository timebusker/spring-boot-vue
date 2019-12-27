package com.timebusker.test;

import com.alibaba.fastjson.JSON;
import com.timebusker.model.MenuEntity;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @Description: CommonTest
 * @Author: Administrator
 * @Date: 2019/12/21 15:46
 **/
public class CommonTest {

    /**
     * 测试反射类数据类型
     *
     * @getDeclaredFields()返回Class中所有的字段，包括私有字段
     * @getFields()只返回公有字段，即有public修饰的字段
     */
    @Test
    public void test1() {
        Field[] fields = new MenuEntity().getClass().getDeclaredFields();
        new MenuEntity().getClass().getFields();
        System.out.println("-------------------------" + fields.length);
        for (Field field : fields) {
            System.out.println(field.getName() + "\t:\t" + field.getType().getName());
        }
    }

    @Test
    public void test2() {
        String reg = "^[^(css)(js)]$";
        System.out.println("http://hhhh/ddd/ccc.cssd".matches(reg));
    }
}
