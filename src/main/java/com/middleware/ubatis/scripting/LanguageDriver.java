package com.middleware.ubatis.scripting;

import com.middleware.ubatis.executor.parameter.ParameterHandler;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.mapping.SqlSource;
import com.middleware.ubatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Eric-ZC
 * @description 脚本语言驱动
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
