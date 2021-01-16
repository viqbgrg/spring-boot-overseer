package com.github.viqbgrg.springbootoverseer.xunlei.zqb.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * json 方法
 *
 * @author viqbgrg
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 字符串转对象
     *
     * @param content
     * @param valueType
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T stringToPOJO(String content, Class<T> valueType) throws JsonProcessingException {
        return mapper.readValue(content, valueType);
    }

    /**
     * 对象转字符串
     *
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public static String pojoToString(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    public static Map<String, Object> stringToObject(String jsonSource) throws JsonProcessingException {
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<>() {
        };
        return mapper.readValue(jsonSource, typeRef);
    }

    public static JsonNode stringToJsonNode(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }

}
