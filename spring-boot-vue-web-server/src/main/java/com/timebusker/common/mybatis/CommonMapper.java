package com.timebusker.common.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @DESC:CommonMapper:定义通用Mapper继承接口
 * @author:timebusker
 * @date:2019/3/14
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {

    // TODO
    // FIXME 特别注意，该接口不能被扫描到，否则会出错
}
