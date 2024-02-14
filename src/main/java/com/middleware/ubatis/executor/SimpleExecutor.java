package com.middleware.ubatis.executor;

import com.middleware.ubatis.executor.statement.StatementHandler;
import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.ResultHandler;
import com.middleware.ubatis.session.RowBounds;
import com.middleware.ubatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Eric-ZC
 * @description 简单执行器
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
