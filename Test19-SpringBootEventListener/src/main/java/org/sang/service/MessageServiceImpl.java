package org.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageEventPublisher messageEventPublisher;

    @Override
    public void somniloquy(String message) {
        System.out.println(new StringBuilder("Thread will wait 3s").toString());
        Thread thread = new Thread("sleep");
        try {
            thread.sleep(1000 * 3); // 1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(new StringBuilder("Wake up").toString());
        System.out.println(message);
    }

    @Override
    public void asyncSay(String message) {
        messageEventPublisher.asyncSendMessage(message);
    }

    @Override
    public void say(String message) {
        messageEventPublisher.sendMessage(message);
    }

}