package com.thc;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class HelloSender1 {

    @Resource
    private PassportMqDao passportMqDao;

    public void send(String msg) throws InterruptedException {
        String msg1 = "I am topic.mesaage msg======";
        User user = null;

        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            user = new User();
            user.setName("name:" + UUID.randomUUID());
            System.out.println(i + " sender1 : " + msg1);
            passportMqDao.sendKnockDoor(user);
            //this.rabbitTemplate.convertAndSend("directExchange","orig_2","origId2::"+msg);
        }
    }

}