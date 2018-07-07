package com.ns.springboothikaricp.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ns.springboothikaricp.bean.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public class JsonRedisDao extends RedisDao{

    @Resource
    private RedisTemplate jsonRedisTemplate;


    public <T> T get(String key,Class<T>  entityClass)  {

         return Optional.ofNullable(get(key)).map(obj-> JSONObject.toJavaObject((JSONObject) obj,entityClass)).orElseGet(()->{return null;});

    }
    @Override
    protected RedisTemplate getRedisTemplate() {
        return jsonRedisTemplate;
    }
}
