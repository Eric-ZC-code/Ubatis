package com.middleware.ubatis.executor.statement;

import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.ResultHandler;
import com.middleware.ubatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Eric-ZC
 * @description 预处理语句处理器（PREPARED）
 */
public class PreparedStatementHandler extends BaseStatementHandler{

    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
    }

    /**
     * 实例化
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    /**
     * 设置参数
     * @param statement
     * @throws SQLException
     */
    @Override
    public void parameterize(Statement statement) throws SQLException {
        parameterHandler.setParameters((PreparedStatement) statement);
    }

    /**
     * 封装结果集
     * @param statement
     * @param resultHandler
     * @return
     * @param <E>
     * @throws SQLException
     */
    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.<E> handleResultSets(ps);
    }

}
