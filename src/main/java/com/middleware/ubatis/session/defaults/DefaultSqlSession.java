package com.middleware.ubatis.session.defaults;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.RowBounds;
import com.middleware.ubatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private Configuration configuration;

    /**
     * SQL执行器
     */
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        log.info("执行查询 statement：{} parameter：{}", statement, JSON.toJSONString(parameter));
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER, ms.getSqlSource().getBoundSql(parameter));
        return list.get(0);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
