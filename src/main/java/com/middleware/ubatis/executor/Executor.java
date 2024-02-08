package com.middleware.ubatis.executor;

import com.middleware.ubatis.mapping.BoundSql;
import com.middleware.ubatis.mapping.MappedStatement;
import com.middleware.ubatis.session.ResultHandler;
import com.middleware.ubatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Eric-ZC
 * @description 执行器
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}