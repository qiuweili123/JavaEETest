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

    @Override
    public <T> void set(String key, T value) {
        super.set(key, String.valueOf(value));
    }

    public long incr(final String key, long incBy) {

        return getRedisTemplate().opsForValue().increment(key, incBy);

    }
}
