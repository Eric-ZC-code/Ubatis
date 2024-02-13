package com.middleware.ubatis.mapping;

/**
 * @author Eric-ZC
 * @description SQL源码
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
