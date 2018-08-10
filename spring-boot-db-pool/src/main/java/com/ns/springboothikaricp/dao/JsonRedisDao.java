package com.ns.springboothikaricp.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class JsonRedisDao extends AbsRedisDao {

    @Resource
    private RedisTemplate jsonRedisTemplate;


    public <T> T get(String key, Class<T> entityClass) {

        return Optional.ofNullable(get(key)).map(obj -> JSONObject.toJavaObject((JSONObject) obj, entityClass)).orElseGet(() -> {
            return null;
        });

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


    @Override
    protected RedisTemplate getRedisTemplate() {
        return jsonRedisTemplate;
    }
}
