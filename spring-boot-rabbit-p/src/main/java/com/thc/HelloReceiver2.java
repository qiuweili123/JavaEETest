package com.thc;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.io.IOException;

//@Component
//@RabbitListener(queues = "topic.message")
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello, Channel channel) throws IOException {
        channel.queueBind("exchange", "topic.message", "topic.message");
        System.out.println("Receiver2  : " + hello);
    }

}