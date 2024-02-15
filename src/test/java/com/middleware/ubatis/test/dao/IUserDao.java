package com.middleware.ubatis.test.dao;

import com.middleware.ubatis.annotations.Delete;
import com.middleware.ubatis.annotations.Insert;
import com.middleware.ubatis.annotations.Select;
import com.middleware.ubatis.annotations.Update;
import com.middleware.ubatis.test.po.User;

import java.util.List;

public interface IUserDao {

    User queryUserInfoById(Long number);

    User queryUserInfo(User user);

    List<User> queryUserInfoList();

    int updateUserInfo(User req);

    void insertUserInfo(User req);

    int deleteUserInfoByUserId(String userId);

/*    @Select("SELECT id, userId, userName, userHead\n" +
            "FROM user\n" +
            "where id = #{id}")
    User queryUserInfoById(Long id);

    @Select("SELECT id, userId, userName, userHead\n" +
            "        FROM user\n" +
            "        where id = #{id}")
    User queryUserInfo(User req);

    @Select("SELECT id, userId, userName, userHead\n" +
            "FROM user")
    List<User> queryUserInfoList();

    @Update("UPDATE user\n" +
            "SET userName = #{userName}\n" +
            "WHERE id = #{id}")
    int updateUserInfo(User req);

    @Insert("INSERT INTO user\n" +
            "(userId, userName, userHead, createTime, updateTime)\n" +
            "VALUES (#{userId}, #{userName}, #{userHead}, now(), now())")
    void insertUserInfo(User req);

    @Delete("DELETE FROM user WHERE userId = #{userId}")
    int deleteUserInfoByUserId(String userId);*/

}
