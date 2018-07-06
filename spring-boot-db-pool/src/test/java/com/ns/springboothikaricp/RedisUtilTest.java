package com.ns.springboothikaricp;

import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.dao.RedisDao;
import com.ns.springboothikaricp.util.RedisCacheUtil;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisUtilTest extends ApplicationTests {

    @Resource
    private RedisDao  redisDao;


    @Test
    public  void  setAndInc() {
        String key="test01";
        redisDao.set(key,10);

        System.out.println(redisDao.incr(key,10)+"##get ddd ::"+redisDao.get(key,Integer.class));
    }


    @Test
    public  void  setAndGetUser() {

        User user =new User();
        user.setId(111);
        user.setUserName("zhangsan");
        user.setAddress("北京市");

        redisDao.set("user:"+user.getId(),user);

    System.out.println("##get ddd ::"+redisDao.get("user:"+user.getId(),User.class).getId());
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




}
