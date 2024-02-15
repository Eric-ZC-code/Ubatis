package com.middleware.ubatis.executor.keygen;

import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @author Eric-ZC
 * @description 不用键值生成器
 */
public class NoKeyGenerator implements KeyGenerator{

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

}
