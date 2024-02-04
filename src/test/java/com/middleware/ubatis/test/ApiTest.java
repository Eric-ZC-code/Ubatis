package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.io.Resources;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.SqlSessionFactoryBuider;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.session.SqlSessionFactory;
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
        Reader reader = Resources.getResourceAsReader("ubatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuider().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
        log.info("测试结果：{}", res);
    }


    @Test
    public void testProxyClass(){
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                (((proxy, method, args) -> "你被代理了"))
        );
        String result = userDao.queryUserName("1");
        log.info("测试结果：{}", JSON.toJSONString(result));
    }
}
