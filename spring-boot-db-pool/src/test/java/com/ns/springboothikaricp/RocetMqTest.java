package com.ns.springboothikaricp;

import com.ns.springboothikaricp.dao.RocketMqDao;
import org.junit.Test;

import javax.annotation.Resource;

public class RocetMqTest extends ApplicationTests {


    @Resource
    private RocketMqDao mqDao;

    @Test
    public void productMsg() throws Exception {

        mqDao.sendMsg("test122");
    }

}
