package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.buider.xml.XMLConfigBuilder;
import com.middleware.ubatis.io.Resources;
import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.SqlSessionFactoryBuilder;
import com.middleware.ubatis.session.defaults.DefaultSqlSession;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.session.SqlSessionFactory;
import com.middleware.ubatis.test.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Proxy;

@Slf4j
public class ApiTest {
    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("ubatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("ubatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("com.middleware.ubatis.test.dao.IUserDao.queryUserInfoById", req);
        log.info("测试结果：{}", JSON.toJSONString(res));
    }
}
