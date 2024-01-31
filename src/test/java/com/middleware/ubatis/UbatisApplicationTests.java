package com.middleware.ubatis;

import com.alibaba.fastjson.JSON;
import com.middleware.ubatis.binding.MapperProxyFactory;
import com.middleware.ubatis.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class UbatisApplicationTests {

	@Test
	public void testMapperProxyFactory(){
		MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

		Map<String, String> sqlSession = new HashMap<>();
		sqlSession.put("com.middleware.ubatis.dao.IUserDao.queryUserName", "模拟执行Mapper.xml中的SQL语句，操作：查询用户名称");

		IUserDao userDao = factory.newInstance(sqlSession);
		String result = userDao.queryUserName("1");
		log.info("测试结果：{}", JSON.toJSONString(result));
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
