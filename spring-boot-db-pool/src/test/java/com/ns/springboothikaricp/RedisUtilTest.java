package com.ns.springboothikaricp;

import com.ns.springboothikaricp.util.RedisCacheUtil;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisUtilTest extends ApplicationTests {

    @Resource
    private RedisCacheUtil<String> redisCacheUtil;

    @Test
    public  void  setAndGet(){

        redisCacheUtil.setCacheObject("test11",10).increment("test12",1);

        System.out.println(""+(1+redisCacheUtil.getCacheObject("test11")));
    }
}
