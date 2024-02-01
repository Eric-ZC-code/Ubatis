package com.middleware.ubatis.session.defaults;

import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.SqlSessionFactory;

public class DefalutSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefalutSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
