package org.sang.service;

import org.sang.bean.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageNotification {

    @Autowired
    private MessageService messageService;

    @EventListener
    public void processMessageEvent(MessageEvent event) throws IOException {
        messageService.somniloquy(event.getMessage());
    }

    @EventListener
    public void processMessageEvent2(MessageEvent event) throws IOException {
        System.out.println("into message evnet2");
        messageService.somniloquy(event.getMessage());
    }
}