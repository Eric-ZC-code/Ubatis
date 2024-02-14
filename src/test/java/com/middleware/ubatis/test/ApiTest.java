package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.io.Resources;
import com.middleware.ubatis.session.*;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.test.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Slf4j
public class ApiTest {

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("ubatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_queryUserInfoById() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L, "10001"));
        log.info("测试结果：{}", JSON.toJSONString(user));
    }
}
