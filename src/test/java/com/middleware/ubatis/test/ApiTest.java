package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.builder.xml.XMLConfigBuilder;
import com.middleware.ubatis.executor.Executor;
import com.middleware.ubatis.io.Resources;
import com.middleware.ubatis.mapping.Environment;
import com.middleware.ubatis.session.*;
import com.middleware.ubatis.session.defaults.DefaultSqlSession;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.test.po.User;
import com.middleware.ubatis.transaction.Transaction;
import com.middleware.ubatis.transaction.TransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

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

        // 获取环境和事务
        Environment environment = configuration.getEnvironment();
        TransactionFactory transactionFactory = environment.getTransactionFactory();
        Transaction tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolatedLevel.READ_COMMITTED, false);

        // 创建执行器
        Executor executor = configuration.newExecutor(tx);

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration,executor);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("com.middleware.ubatis.test.dao.IUserDao.queryUserInfoById", req);
        log.info("测试结果：{}", JSON.toJSONString(res));
    }
}
