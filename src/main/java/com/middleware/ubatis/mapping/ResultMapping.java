package com.middleware.ubatis.mapping;

import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.type.JdbcType;
import com.middleware.ubatis.type.TypeHandler;

/**
 * @author Eric-ZC
 * @description 结果映射
 */
public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }

}
