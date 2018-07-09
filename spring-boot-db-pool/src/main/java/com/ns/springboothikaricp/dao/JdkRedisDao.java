package com.ns.springboothikaricp.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("jdkRedisDao")
public class JdkRedisDao extends AbsRedisDao {


    @Resource
    private RedisTemplate  jdkRedisTemplate;

    @Override
    protected RedisTemplate getRedisTemplate() {
        return jdkRedisTemplate;
    }
}
