package com.middleware.ubatis.session;

import com.middleware.ubatis.buider.xml.XMLConfigBuilder;
import com.middleware.ubatis.session.defaults.DefalutSqlSessionFactory;

import java.io.Reader;

/**
 * @description 构建SqlSessionFactory的工厂
 * @author Eric-ZC
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefalutSqlSessionFactory(configuration);
    }
}
