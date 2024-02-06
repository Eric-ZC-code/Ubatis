package com.middleware.ubatis.transaction.jdbc;

import com.middleware.ubatis.session.TransactionIsolatedLevel;
import com.middleware.ubatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description JDBC 事务，直接利用 JDBC 的 commit、rollback。依赖于数据源获得的连接来管理事务范围。
 * @author Eric-ZC
 */
public class JdbcTransaction implements Transaction {

    private Connection connection;
    private DataSource dataSource;
    private TransactionIsolatedLevel transactionIsolatedLevel = TransactionIsolatedLevel.None;
    private boolean autoCommit;

    public JdbcTransaction(DataSource dataSource, TransactionIsolatedLevel transactionIsolatedLevel, boolean autoCommit) {
        this.dataSource = dataSource;
        this.transactionIsolatedLevel = transactionIsolatedLevel;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        connection.setAutoCommit(autoCommit);
        connection.setTransactionIsolation(transactionIsolatedLevel.getLevel());
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if (connection != null && !connection.getAutoCommit()){
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (connection != null && !connection.getAutoCommit()){
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.getAutoCommit()){
            connection.close();
        }
    }
}
