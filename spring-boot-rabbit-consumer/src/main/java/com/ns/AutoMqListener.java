package com.ns;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * Created by lenovo on 2017/11/14.
 */
public class AutoMqListener extends AbsMqListener<User> {

    @Override
    public void handerMessage(User obj, Message message, Channel channel) {
        System.out.println("AutoMqListener ##name=" + obj.getName());
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
