package com.middleware.ubatis.test.plugin;


import com.middleware.ubatis.datasource.DataSourceContextHolder;
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

        // 日志监控
        log(sql);

        // 库表路由
        route(sql);

        // 字段加解密
        encrypt(sql);

        // 放行
        return invocation.proceed();
    }

    private void log(String sql) {
        System.out.println("拦截SQL：" + sql);
    }

    private void route(String sql) {
        // 根据sql中的表名进行路由到不同的数据源
        if (sql.contains("user")) {
            DataSourceContextHolder.setDataSource("POOLED");
        } else {
            DataSourceContextHolder.setDataSource("UNPOOLED");
        }
    }

    private void encrypt(String sql) {
        // 对SQL进行字段加解密处理
    }

    @Override
    public void setProperties(Properties properties) {
        for (Map.Entry<Object, Object> entry: properties.entrySet()) {
            System.out.println("参数输出：" + entry.getValue());
        }
    }

}
