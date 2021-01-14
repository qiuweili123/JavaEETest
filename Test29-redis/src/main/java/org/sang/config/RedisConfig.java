package org.sang.config;

import org.sang.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
public class RedisConfig {

    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    /**
     * 一个redisTemplate多种机制
     * @return
     */
    @Bean
    public RedisUtil redisUtil(){
      return new RedisUtil(redisTemplate);
    }
    @Bean("stringRedisUtil")
    public RedisUtil sringRedisUtil(){
        RedisUtil redisUtil = new RedisUtil(redisTemplate);
        redisUtil.setHashValueSerializer(new StringRedisSerializer());
        redisUtil.setValueSerializer(new StringRedisSerializer());
        return redisUtil;
    }
}
