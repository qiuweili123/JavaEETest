package com.ns.springboothikaricp.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.util.GenericsUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public abstract class RedisDao {

   /* private Class<T> entityClass;

    public RedisDao(){

       entityClass= GenericsUtils.getSuperClassGenricType(this.getClass());
        System.out.println("##entityCalss=="+entityClass);
    }

*/



    /**
     * 写入缓存
     *
     * @param key   key
     * @param value value
     * @return true表示成功
     */
    public <T> void set(final String key, T value) {

        ValueOperations<String,T> operations = getRedisTemplate().opsForValue();


        operations.set(key,value);
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key   key
     * @param value value
     * @return true表示成功
     */
    public <T> void set(final String key, T value, Long expireTime) {
        ValueOperations<String, T> operations = getRedisTemplate().opsForValue();

        operations.set(key,  value, expireTime,TimeUnit.SECONDS);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */

    /*public  T get(final String key) {

        ValueOperations<String,T> operations = redisTemplate.opsForValue();

       return   operations.get(key);

    }*/
    public <T> T get(final String key) {

        ValueOperations<String,T> operations = getRedisTemplate().opsForValue();

        return    operations.get(key);

    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public boolean delete(final String key) {
       return  getRedisTemplate().delete(key);
    }

    /**
     * 批量删除
     * @param keys
     * @return
     */
    public boolean deleteBatch(final String ... keys) {
        return  getRedisTemplate().delete(keys);
    }


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return getRedisTemplate().hasKey(key);
    }


    /**
     * 原子自增
     *
     * @param key
     * @param incBy 负值表示decr
     * @return
     */
    public long incr(final String key, long incBy) {

        return getRedisTemplate().opsForValue().increment(key, incBy);

    }

    /**
     * 设置key过期时长
     *
     * @param key
     * @return
     */
    public boolean expire(final String key, long expireSeconds) {
          return   getRedisTemplate().expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 指定的时间点失效
     * @param key
     * @param date
     * @return
     */
    public boolean expireAt(final String key, Date date) {
        return   getRedisTemplate().expireAt(key,date);
    }


  protected abstract   RedisTemplate getRedisTemplate();

}
