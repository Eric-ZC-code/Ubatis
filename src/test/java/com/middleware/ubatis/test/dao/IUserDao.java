package com.middleware.ubatis.test.dao;

import com.middleware.ubatis.test.po.User;

public interface IUserDao {

    User queryUserInfoById(Long number);

    User queryUserInfo(User user);

}
