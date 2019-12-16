package com.rpa.server.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: velve
 * @date: Created in 2019/10/20 19:01
 * @description: 在 Money 与 Long 之间转换的 TypeHandler，处理 CNY 人民币
 * @version: 1.0
 */
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        System.out.println("setNonNullParameter======"+preparedStatement+"--"+i+"--"+money+"--"+jdbcType);
        preparedStatement.setLong(i, money.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        System.out.println("getNullableResult======"+resultSet.toString()+"--"+columnName);
        return parseMoney(resultSet.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return parseMoney(resultSet.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return parseMoney(callableStatement.getLong(columnIndex));
    }

    private Money parseMoney(Long value) {
        System.out.println("value===="+value);
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }

}
