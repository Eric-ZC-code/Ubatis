package com.middleware.ubatis.session.defaults;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.mapping.Environment;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.SqlSessionFactory;
import com.middleware.ubatis.session.TransactionIsolatedLevel;
import com.middleware.ubatis.transaction.Transaction;
import com.middleware.ubatis.transaction.TransactionFactory;

import java.sql.SQLException;

public class DefalutSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefalutSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolatedLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
