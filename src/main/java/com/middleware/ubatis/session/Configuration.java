package com.middleware.ubatis.session;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.datasource.druid.DruidDataSourceFactory;
import com.middleware.ubatis.datasource.pooled.PooledDataSourceFactory;
import com.middleware.ubatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.executor.SimpleExecutor;
import com.middleware.ubatis.executor.parameter.ParameterHandler;
import com.middleware.ubatis.executor.resultset.DefaultResultSetHandler;
import com.middleware.ubatis.executor.resultset.ResultSetHandler;
import com.middleware.ubatis.executor.statement.PreparedStatementHandler;
import com.middleware.ubatis.executor.statement.StatementHandler;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.Environment;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.mapping.ResultMap;
import com.middleware.ubatis.reflection.MetaObject;
import com.middleware.ubatis.reflection.factory.DefaultObjectFactory;
import com.middleware.ubatis.reflection.factory.ObjectFactory;
import com.middleware.ubatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.middleware.ubatis.reflection.wrapper.ObjectWrapperFactory;
import com.middleware.ubatis.scripting.LanguageDriver;
import com.middleware.ubatis.scripting.LanguageDriverRegistry;
import com.middleware.ubatis.scripting.xmltags.XMLLanguageDriver;
import com.middleware.ubatis.session.defaults.DefaultSqlSession;
import com.middleware.ubatis.transaction.Transaction;
import com.middleware.ubatis.transaction.jdbc.JdbcTransactionFactory;
import com.middleware.ubatis.type.TypeAliasRegistry;
import com.middleware.ubatis.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description 配置类
 * @author Eric-ZC
 */
public class Configuration {

    // 环境
    protected Environment environment;

    // 注册映射器
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    // 结果映射，存在Map里
    protected final Map<String, ResultMap> resultMaps = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, rowBounds, resultHandler, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
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

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    public String getDatabaseId() {
        return databaseId;
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public void addResultMap(ResultMap resultMap) {
        resultMaps.put(resultMap.getId(), resultMap);
    }

    public ResultMap getResultMap(String resultMapName) {
        return resultMaps.get(resultMapName);
    }
}
