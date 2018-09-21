package org.sang.service;

import org.sang.bean.MessageEvent;
import org.sang.bean.MessageEvent2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void sendMessage(String message) {
        MessageEvent2 event = new MessageEvent2(this, message);
        System.out.println(new StringBuilder("Send Event").toString());
        publisher.publishEvent(event);
        System.out.println(new StringBuilder("Event Done").toString());
    }

    @Async
    public void asyncSendMessage(String message) {
        MessageEvent event = new MessageEvent(this, message);
        System.out.println(new StringBuilder("Send Async Event").toString());
        publisher.publishEvent(event);
        System.out.println(new StringBuilder("Async Event Done").toString());
    }
}