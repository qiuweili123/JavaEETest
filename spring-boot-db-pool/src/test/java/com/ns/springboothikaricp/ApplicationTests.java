package com.ns.springboothikaricp;

import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.dao.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    private UserInfoMapper userInfoMapper;


    @Test
    public void testDynamicDatasource() {
        User userInfo;
        for (int i = 1; i <= 2; i++) {
            //i为奇数时调用selectByOddUserId方法获取，i为偶数时调用selectByEvenUserId方法获取
            userInfo = i % 2 == 1 ? userInfoMapper.selectByOddUserId(new Long(i)) : userInfoMapper.selectByEvenUserId(new Long(i));
            System.out.println("##" + userInfo.getUserName());

        }
    }


}
