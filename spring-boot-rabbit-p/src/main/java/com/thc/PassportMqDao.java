package com.thc;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/9/14.
 */

@Component
public class PassportMqDao extends AbsNsMqDao {

    public PassportMqDao(ConnectionFactory connectionFactory, PassportConfig config) {
        super(connectionFactory, config);
    }


    public void sendKnockDoor(User user) {
        String routingKey = "orig_1";
        //thisgetRabbitTemplate().convertAndSend(routingKey, user);
        convertAndSend(routingKey, user);
    }

}
