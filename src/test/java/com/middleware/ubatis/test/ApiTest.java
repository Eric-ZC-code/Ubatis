package com.middleware.ubatis.test;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.io.Resources;
import com.middleware.ubatis.session.*;
import com.middleware.ubatis.test.dao.IActivityDao;
import com.middleware.ubatis.test.dao.IUserDao;
import com.middleware.ubatis.test.po.Activity;
import com.middleware.ubatis.test.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
    public void test_insertUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        User user = new User();
        user.setUserId("10002");
        user.setUserName("小白");
        user.setUserHead("1_05");
        userDao.insertUserInfo(user);
        log.info("测试结果：{}", "Insert OK");

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.deleteUserInfoByUserId("10002");
        log.info("测试结果：{}", count == 1);

        // 3. 提交事务
        sqlSession.commit();
    }


    @Test
    public void test_updateUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.updateUserInfo(new User(1L, "10001", "叮当猫"));
        log.info("测试结果：{}", count);

        // 3. 提交事务
        sqlSession.commit();
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

    @Test
    public void test_queryUserInfoList() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        log.info("测试结果：{}", JSON.toJSONString(users));
    }

    @Test
    public void test_queryActivityById(){
        // 1. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        // 2. 测试验证
        Activity req = new Activity();
        req.setActivityId(10001L);

        log.info("测试结果：{}", JSON.toJSONString(dao.queryActivityById(req)));
        sqlSession.commit();
//        sqlSession.clearCache();
//        sqlSession.close();
        log.info("测试结果：{}", JSON.toJSONString(dao.queryActivityById(req)));
    }

    @Test
    public void test_insertActivity() {
        // 1. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        Activity activity = new Activity();
        activity.setActivityId(10006L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试数据插入");
        activity.setCreator("闷油瓶");

        // 2. 测试验证
        Integer res = dao.insert(activity);
        sqlSession.commit();
        log.info("测试结果：count：{} idx：{}", res, JSON.toJSONString(activity.getId()));
    }
}
