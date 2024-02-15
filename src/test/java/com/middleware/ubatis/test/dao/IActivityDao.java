package com.middleware.ubatis.test.dao;

import com.middleware.ubatis.test.po.Activity;

public interface IActivityDao {

    Activity queryActivityById(Long activityId);

}
