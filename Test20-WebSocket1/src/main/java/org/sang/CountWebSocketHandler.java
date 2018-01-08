package org.sang;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CountWebSocketHandler extends TextWebSocketHandler {
  
    private static long count = 0;  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("你是第" + (++count) + "位访客"));  
    }  
}  