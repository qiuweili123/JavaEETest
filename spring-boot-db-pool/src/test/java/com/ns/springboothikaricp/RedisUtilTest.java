package com.ns.springboothikaricp;

import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.dao.JdkRedisDao;
import com.ns.springboothikaricp.dao.JsonRedisDao;
import com.ns.springboothikaricp.dao.StringRedisDao;
import com.ns.springboothikaricp.util.RedisCacheUtil;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisUtilTest extends ApplicationTests {


    @Resource
    private StringRedisDao stringRedisDao;

    @Resource
    private JdkRedisDao jdkRedisDao;

    @Resource
    private JsonRedisDao jsonRedisDao;

    @Resource

    private RedisCacheUtil redisCacheUtil;


    @Test
    public void setAndInc() {
        String key = "test01";
        //会出现Integer强制转换异常
        // stringRedisDao.set(key,10);
        /*//序列化乱码，不能使用原子增加
         jdkRedisDao.set(key,10);
         jdkRedisDao.incr(key,10);
        System.out.println("##value::"+jdkRedisDao.get(key));*/
        //可以使用inc
        jsonRedisDao.set(key, 100L);
        jsonRedisDao.incr(key, 1);
        System.out.println("" + jsonRedisDao.get(key));
        //System.out.println(redisDao.incr(key,10)+"##get ddd ::"+redisDao.get(key,Integer.class));
    }

    @Test
    public void setAndGetUser() {

        User user = new User();
        user.setId(111);
        user.setUserName("zhangsan");
        user.setAddress("北京市");

        String key = "user:" + user.getId();
        jsonRedisDao.set(key, user);
        // System.out.println("user::"+jsonRedisDao.get(key+"001",User.class));

    }


}
