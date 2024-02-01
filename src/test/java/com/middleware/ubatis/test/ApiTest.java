package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.binding.MapperRegistry;
import com.middleware.ubatis.session.SqlSession;
import com.middleware.ubatis.session.defaults.DefalutSqlSessionFactory;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.session.SqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class ApiTest {
    @Test
    public void testMapperProxyFactory() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.middleware.ubatis.test.dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefalutSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserName("10001");
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
