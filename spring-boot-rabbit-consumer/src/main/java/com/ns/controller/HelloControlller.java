package com.ns.controller;

import com.ns.AutoMqListener;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lenovo on 2017/12/3.
 */
@RestController
@RequestMapping("/springboot")
public class HelloControlller {
    @Resource
 private ConnectionFactory connectionFactory;
    @Resource
    private SimpleMessageListenerContainer autoMessageLisner;

    @RequestMapping("/hello")
    public String hello(String id) throws IOException {
        String queueName="direct.queue.test"+id;
        Channel channel = connectionFactory.createConnection().createChannel(true);
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, false, false, true, null);
        channel.queueBind(queueName,"direct.exchange.test","orig_1");
        autoMessageLisner.removeQueueNames("direct.queue.test");
        autoMessageLisner.addQueueNames(queueName);

        return "hello";
    }
}
