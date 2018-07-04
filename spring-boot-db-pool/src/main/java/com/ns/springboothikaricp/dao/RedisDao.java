package com.ns.springboothikaricp.dao;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {

     @Resource
     private RedisTemplate<String,Object> redisTemplate;


     /**
      * 写入缓存
      *
      * @param key   key
      * @param value value
      * @return true表示成功
      */
     public boolean set(final String key, Object value) {
          try {
               ValueOperations<String, Object> operations = redisTemplate.opsForValue();
               operations.set(key, value);
               return true;
          } catch (Exception e) {
               throw e;
          }

     }

     /**
      * 写入缓存设置时效时间
      *
      * @param key   key
      * @param value value
      * @return true表示成功
      */
     public boolean set(final String key, Object value, Long expireTime) {
          try {
               ValueOperations<String, Object> operations = redisTemplate.opsForValue();
               operations.set(key, value);
               redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
               return true;
          } catch (Exception e) {
               throw e;
          }
     }

     /**
      * 删除对应的value
      *
      * @param key
      */
     public void remove(final String key) {
          if (exists(key)) {
               redisTemplate.delete(key);
          }
     }

     /**
      * 判断缓存中是否有对应的value
      *
      * @param key
      * @return
      */
     public boolean exists(final String key) {
          return redisTemplate.hasKey(key);
     }

     /**
      * 读取缓存
      *
      * @param key
      * @return
      */
     public Object get(final String key) {
          ValueOperations<String, Object> operations = redisTemplate.opsForValue();
          return operations.get(key);
     }

     /**
      * 原子自增
      *
      * @param key
      * @param incBy
      * @return
      */
     public long incr(final String key, long incBy) {
          try {
               return redisTemplate.opsForValue().increment(key, incBy);
          } catch (Exception e) {
               throw e;
          }
     }

     /**
      * 设置key过期时长
      *
      * @param key
      * @return
      */
     public void expire(final String key, long expireSeconds) {
          try {

               //redisTemplate.execute(c -> c.exists(key.getBytes()));
               redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
          } catch (Exception e) {
               throw e;
          }
     }
}
