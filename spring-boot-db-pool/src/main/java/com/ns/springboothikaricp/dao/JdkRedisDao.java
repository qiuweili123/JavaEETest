package com.ns.springboothikaricp.dao;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("jdkRedisDao")
public class JdkRedisDao extends RedisDao {


    @Resource
    private RedisTemplate  jdkRedisTemplate;

    @Override
    protected RedisTemplate getRedisTemplate() {
        return jdkRedisTemplate;
    }
}
