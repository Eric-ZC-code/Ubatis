package com.middleware.ubatis.session.defaults;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.SqlSessionFactory;

public class DefalutSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefalutSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(this.configuration);
    }
}
