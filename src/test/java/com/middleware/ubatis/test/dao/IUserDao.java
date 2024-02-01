package com.middleware.ubatis.test.dao;

public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
