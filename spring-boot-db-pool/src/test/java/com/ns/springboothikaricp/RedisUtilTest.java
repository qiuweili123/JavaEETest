package com.ns.springboothikaricp;

import com.alibaba.fastjson.JSON;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.dao.RedisDao;
import com.ns.springboothikaricp.dao.StringRedisDao;
import com.ns.springboothikaricp.util.RedisCacheUtil;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisUtilTest extends ApplicationTests {

    @Resource
    private RedisDao  redisDao;

    @Resource
    private StringRedisDao stringRedisDao;

    @Resource

    private RedisCacheUtil redisCacheUtil;


    @Test
    public  void  setAndInc() {
        String key="test01";
        stringRedisDao.set(key,10);

        //System.out.println(redisDao.incr(key,10)+"##get ddd ::"+redisDao.get(key,Integer.class));
    }


    @Test
    public  void  setAndGetUser() {

        User user =new User();
        user.setId(111);
        user.setUserName("zhangsan");
        user.setAddress("北京市");

        redisDao.set("user:"+user.getId(),user);

    //System.out.println("##get ddd ::"+redisDao.get("user:"+user.getId(),User.class).getId());
    }

    @Test
    public  void  setAndGetUser2() {

        User user =new User();
        user.setId(111);
        user.setUserName("zhangsan");
        user.setAddress("北京市");

        redisDao.set("user:"+user.getId(),user,10000L);

        System.out.println("##get ddd ::"+redisDao.get("user:"+user.getId()));
    }

//这种使用fastjsonredisserilzaer没有问题
    @Test
    public void  setAndGetstr(){
        String key="test02";
        redisCacheUtil.setCacheObject(key,1).increment(key,2);
        System.out.println(""+redisCacheUtil.getCacheObject(key));
    }
    @Test
    public  void  setAndGetUser3() {

        User user =new User();
        user.setId(111);
        user.setUserName("zhangsan");
        user.setAddress("北京市");

        redisCacheUtil.setCacheObject("user:"+user.getId(),user);

        System.out.println("##get ddd ::"+ redisCacheUtil.getCacheObject("user:"+user.getId()));
    }




}
