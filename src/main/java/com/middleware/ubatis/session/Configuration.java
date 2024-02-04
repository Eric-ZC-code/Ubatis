package com.middleware.ubatis.session;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.defaults.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    /**
     * 注册映射器
     */
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 注册SQL语句
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public void addMapper(Class<?> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, DefaultSqlSession defaultSqlSession) {
        return mapperRegistry.getMapper(type, defaultSqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public MappedStatement getMappedStatement(String statement) {
        return mappedStatements.get(statement);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

}
