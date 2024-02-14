package com.middleware.ubatis.executor.statement;

import com.middleware.ubatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Eric-ZC
 * @description 语句处理器
 */
public interface StatementHandler {

    /** 准备语句 */
    Statement prepare(Connection connection) throws SQLException;

    /** 参数化 */
    void parameterize(Statement statement) throws SQLException;

    /** 执行查询 */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    /** 执行增删改 */
    int update(Statement stmt) throws SQLException;
}
