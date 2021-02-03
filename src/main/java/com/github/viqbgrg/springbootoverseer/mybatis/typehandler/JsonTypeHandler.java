package com.github.viqbgrg.springbootoverseer.mybatis.typehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.BalanceInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.Devices;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.MineInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.ProduceStat;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes({MineInfo.class, BalanceInfo.class, Devices.class, ProduceStat.class, Integer[].class, List.class})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private Class<T> type;


    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, objectMapper.writeValueAsString(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value != null)
            return objectMapper.convertValue(value, type);
        return null;
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value != null)
            return objectMapper.convertValue(value, type);
        return null;
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value != null)
            return objectMapper.convertValue(value, type);
        return null;
    }
}
