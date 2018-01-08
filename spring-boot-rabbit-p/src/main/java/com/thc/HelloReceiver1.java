package com.thc;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class HelloReceiver1 {
//spel写法
    //@RabbitListener(queues = "#{queueMessage.name}")

    //@RabbitHandler
    public void process(User user) throws IOException {
        // channel.queueBind("exchange", "topic.message","topic.message");
        System.out.println("Receiver1  : " + user.getName());
    }

    // @RabbitListener(queues = "#{amessage.name}")
    public void process2(String hello) throws IOException {
        // channel.queueBind("exchange", "topic.message","topic.message");
        System.out.println("Receiver1  : " + hello);
    }


}