package com.thc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitApplicationTests {
    @Resource
    private HelloSender1 helloSender1;

    @Test
    public void testSend() throws InterruptedException {
        helloSender1.send("sd112455");
    }
}
