package com.cn.my.common.starter.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;


/**
 * objectmapper通用类
 *
 * @author songshuai
 */
public class JsonUtil {

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    /**
     * 获取可供后续定制化的objectReader,该类型线程安全，可以共享
     *
     * @param clazz 类型
     * @return objectReader
     */
    public static ObjectReader getObjectReader(Class<?> clazz) {
        return OBJECTMAPPER.readerFor(clazz);
    }

    /**
     * 获取可供后续定制化的objectReader,该类型线程安全，可以共享
     *
     * @param type 类型
     * @return objectReader
     */
    public static ObjectReader getObjectReader(TypeReference<?> type) {
        return OBJECTMAPPER.readerFor(type);
    }

    /**
     * 获取objectMapper以实现高级功能，请勿进行<b>任何方式的定制化</b>
     *
     * @return objectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECTMAPPER;
    }

    /**
     * 获取可供定制化的objectWriter，该类型线程安全，可以共享
     *
     * @return objectWriter
     */
    public static ObjectWriter getObjectWriter() {
        return OBJECTMAPPER.writer();
    }


}
