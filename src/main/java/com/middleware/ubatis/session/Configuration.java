package com.middleware.ubatis.session;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.datasource.druid.DruidDataSourceFactory;
import com.middleware.ubatis.datasource.pooled.PooledDataSourceFactory;
import com.middleware.ubatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.middleware.ubatis.mapping.Environment;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.defaults.DefaultSqlSession;
import com.middleware.ubatis.transaction.jdbc.JdbcTransactionFactory;
import com.middleware.ubatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 配置类
 * @author Eric-ZC
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 注册映射器
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);


    // 注册SQL语句
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

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

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
