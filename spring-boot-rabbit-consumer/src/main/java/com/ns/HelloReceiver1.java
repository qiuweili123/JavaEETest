package com.ns;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

//@Component
public class HelloReceiver1  {

    //spel写法
   // @RabbitListener(queues = "${passportConsumer.queueName}")
 // @RabbitListener(queues = "#{passportLisenerConfig.queueName}")
 //     @RabbitListener(queues = "queueMessage")

    //@RabbitHandler
/*    public void process(User user) throws IOException {
        // channel.queueBind("exchange", "topic.message","topic.message");
        System.out.println("Receiver1  : " + user.getName());
    }*/

    // @RabbitListener(queues = "#{amessage.name}")
    public void process2(String hello) throws IOException {
        // channel.queueBind("exchange", "topic.message","topic.message");
        System.out.println("Receiver1  : " + hello);
    }



}