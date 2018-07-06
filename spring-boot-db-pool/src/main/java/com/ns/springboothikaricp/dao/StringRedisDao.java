package com.ns.springboothikaricp.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StringRedisDao extends RedisDao {

  @Resource
  private  RedisTemplate stringRedisTemplate;

    @Override
    protected RedisTemplate getRedisTemplate() {
        return stringRedisTemplate;
    }
}
