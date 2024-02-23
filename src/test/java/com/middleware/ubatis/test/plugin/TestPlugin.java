package com.middleware.ubatis.test.plugin;


import com.middleware.ubatis.executor.statement.StatementHandler;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.plugin.Interceptor;
import com.middleware.ubatis.plugin.Intercepts;
import com.middleware.ubatis.plugin.Invocation;
import com.middleware.ubatis.plugin.Signature;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class TestPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 获取SQL信息
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        // 输出SQL
        System.out.println("拦截SQL：" + sql);
        // 放行
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        for (Map.Entry<Object, Object> entry: properties.entrySet()) {
            System.out.println("参数输出：" + entry.getValue());
        }
    }

}
