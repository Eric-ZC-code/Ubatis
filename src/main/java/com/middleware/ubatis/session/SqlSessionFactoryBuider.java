package com.middleware.ubatis.session;

import com.middleware.ubatis.buider.xml.XMLConfigBuider;
import com.middleware.ubatis.session.defaults.DefalutSqlSessionFactory;

import java.io.Reader;

/**
 * 构建SqlSessionFactory的工厂
 * @author Eric
 */
public class SqlSessionFactoryBuider {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuider xmlConfigBuider = new XMLConfigBuider(reader);
        return build(xmlConfigBuider.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefalutSqlSessionFactory(configuration);
    }
}
