//package com.github.viqbgrg.springbootoverseer.mybatis.typehandler;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.viqbgrg.springbootoverseer.entity.PdcDetail;
//import com.github.viqbgrg.springbootoverseer.entity.ProduceStatHistory;
//import com.github.viqbgrg.springbootoverseer.entity.SpeedStat;
//import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.MineInfo;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedTypes;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@MappedTypes({List.class})
//public class ListHandler<T> extends BaseTypeHandler<List<T>> {
//
//    private Class<T> type;
//    private TypeReference<List<T>> typeRef;
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public ListHandler(Class<T> type) {
//        if (type == null) {
//            throw new IllegalArgumentException("Type argument cannot be null");
//        }
//        this.type = type;
//        this.typeRef = new TypeReference<List<T>>() {
//        };
//    }
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
//        try {
//            ps.setString(i, objectMapper.writeValueAsString(parameter));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        String value = rs.getString(columnName);
//        if (value != null) {
//            try {
//                return objectMapper.readValue(value, typeRef);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String value = rs.getString(columnIndex);
//        if (value != null) {
//            try {
//                return objectMapper.readValue(value, typeRef);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String value = cs.getString(columnIndex);
//        if (value != null) {
//            try {
//                return objectMapper.readValue(value, typeRef);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//}
