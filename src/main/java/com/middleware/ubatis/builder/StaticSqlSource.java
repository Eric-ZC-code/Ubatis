package com.middleware.ubatis.builder;

import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.ParameterMapping;
import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.session.Configuration;

import java.util.List;

/**
 * @author Eric-ZC
 * @description 静态SQL源码
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }

}
