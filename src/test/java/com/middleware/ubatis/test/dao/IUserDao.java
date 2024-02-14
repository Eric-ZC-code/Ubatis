package com.middleware.ubatis.test.dao;

import com.middleware.ubatis.test.po.User;

import java.util.List;

public interface IUserDao {

    User queryUserInfoById(Long number);

    User queryUserInfo(User user);

    List<User> queryUserInfoList();

    int updateUserInfo(User req);

    void insertUserInfo(User req);

    int deleteUserInfoByUserId(String userId);

}
