package com.ns.springboothikaricp.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StringRedisDao extends AbsRedisDao {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected RedisTemplate getRedisTemplate() {
        return stringRedisTemplate;
    }
}
