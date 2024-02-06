package com.middleware.ubatis.transaction.jdbc;

import com.middleware.ubatis.session.TransactionIsolatedLevel;
import com.middleware.ubatis.transaction.Transaction;
import com.middleware.ubatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @description Jdbc事务工厂
 * @author Eric-ZC
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolatedLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
