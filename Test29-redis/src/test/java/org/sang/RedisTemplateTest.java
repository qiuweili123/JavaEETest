package org.sang;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.sang.bean.Person;
import org.sang.util.RedisUtil;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTemplateTest extends ApplicationTests {
    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    @Test
    public void  testHash2(){
        String key="testhash-003";
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("name","lisi");
        map.put("age",40);
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setHashKeySerializer( new StringRedisSerializer());
        redisTemplate.opsForHash().putAll(key,map);

       // redisTemplate.opsForValue().set("string",12);
    }

    @Test
    public void  testHash4(){
        String key="testhash-004";
        Map<Integer,Long> map= new HashMap<Integer, Long>();
        map.put(1,100L);
        map.put(2,200L);
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(  new GenericFastJsonRedisSerializer());

        redisTemplate.opsForHash().putAll(key,map);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        HashOperations<String, Integer, Long> operations = redisTemplate.opsForHash();
        List<Long> longs = operations.multiGet(key,list);
        System.out.println("longs=="+longs);
    }


}
