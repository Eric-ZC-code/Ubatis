package com.middleware.ubatis.test.dao;

public interface IUserDao {

    String queryUserInfoById(String number);

    String queryUserName(String number);
}
