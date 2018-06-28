package com.ns.springboothikaricp.dao;

import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.config.TargetDataSource;


public interface UserInfoMapper {
    /**
     * 从test1数据源中获取用户信息
     */
    @TargetDataSource("test1")
    User selectByOddUserId(Long id);

    /**
     * 从test2数据源中获取用户信息
     */
    @TargetDataSource("test1")
    User selectByEvenUserId(Long id);

    int insert(User user);
}