package com.timebusker.common.mybatis;

import org.apache.ibatis.type.*;

import java.sql.*;

/**
 * @DESC:BitTypeHandler:postgresql数据库bit数据转换boolean
 * @author:timebusker
 * @date:2019/3/15
 */
@MappedJdbcTypes({JdbcType.BIT})
@MappedTypes({Boolean.class})
public class BitTypeHandler extends BaseTypeHandler<Boolean> {

    /**
     * 写库时数据转化
     *
     * @param preparedStatement
     * @param i
     * @param flag
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean flag, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf((flag ? 1 : 0)));
    }

    /**
     * 读库时数据转化
     *
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getBoolean(s);
    }

    /**
     * 读库时数据转化
     *
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBoolean(i);
    }

    /**
     * 读库时数据转化
     *
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getBoolean(i);
    }
}
