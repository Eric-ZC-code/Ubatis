package com.middleware.ubatis.scripting.defaults;

import com.middleware.ubatis.builder.SqlSourceBuilder;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.scripting.xmltags.DynamicContext;
import com.middleware.ubatis.scripting.xmltags.SqlNode;
import com.middleware.ubatis.session.Configuration;

import java.util.HashMap;

/**
 * @author Eric-ZC
 * @description 原始SQL源码，比 DynamicSqlSource 动态SQL处理快
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}
