package com.middleware.ubatis.scripting.xmltags;

import com.middleware.ubatis.executor.parameter.ParameterHandler;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.scripting.LanguageDriver;
import com.middleware.ubatis.scripting.defaults.DefaultParameterHandler;
import com.middleware.ubatis.scripting.defaults.RawSqlSource;
import com.middleware.ubatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Eric-ZC
 * @description XML语言驱动器
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        return new RawSqlSource(configuration, sql, parameterType);
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }

}