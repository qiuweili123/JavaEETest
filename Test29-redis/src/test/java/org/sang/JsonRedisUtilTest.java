package org.sang;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.sang.bean.Person;
import org.sang.util.RedisUtil;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sun.rmi.runtime.NewThreadAction;

import javax.annotation.Resource;
import java.util.*;

public class JsonRedisUtilTest extends ApplicationTests {
    @Resource
    private RedisTemplate<String, ?> redisTemplate;
    @Resource
    private RedisUtil redisUtil;
    @Test
    public void setAndInc() {
       /* String key = "intKey01";
        redisUtil.set(key, 100);
        redisUtil.incr(key, 1);
        System.out.println("" + redisUtil.get(key,Integer.class));
        //System.out.println(redisDao.incr(key,10)+"##get ddd ::"+redisDao.get(key,Integer.class));*/
    }

    @Test
    public void setAndGetUser() {
        Person user = new Person();
        user.setId(111L);
        user.setName("zhangsan");
        user.setAddress("北京市");
        String key = "user:" + user.getId();
        redisUtil.set(key, user);
    }

    /**
     * 推荐对于hash结构存储使用value使用json序列化，如果使用string需要将每个entry的value转化为string
     */
    @Test
    public void  testSetHash(){
     /*   Map<String,Object> map= new HashMap<String, Object>();
        map.put("name","zhangsan");
        map.put("age",40);
        redisUtil.hMSet("testhash-001",map);
        Map<String, Object> map1 = redisUtil.hGetAll("testhash-001", String.class, Object.class);
        System.out.println(map1);
        redisUtil.hIncrBy("testhash-001","age",1L);
        Map<String, Object> map2 = redisUtil.hGetAll("testhash-001", String.class, Object.class);
        System.out.println("after inc:"+map2);
        Map<String, String> map3 = redisUtil.hMGet("testhash-001","age", "name");
        System.out.println("after map3 inc:"+map3);
        List<Object> list = new ArrayList<Object>();
        list.add("age");

        redisTemplate.opsForHash().putAll("testhash-002",map);

        List<Object> age = redisTemplate.opsForHash().multiGet("testhash-002",list);
        System.out.println("after map4 inc:"+age);*/
    }

    @Test
    public void  testHash2(){
   String key="testhash-003";
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("name","lisi");
        map.put("age",40);
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setHashKeySerializer( new StringRedisSerializer());
        redisTemplate.opsForHash().putAll(key,map);
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
    @Test
    public void testSetSortList(){


    }



}
