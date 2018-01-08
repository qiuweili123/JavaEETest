package org.sang;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by sang on 16-12-22.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //必须加/ws/ 否则不行  此处为连接点信息
             stompEndpointRegistry.addEndpoint("/ws/endpointSang").setAllowedOrigins("*").withSockJS();

        //stompEndpointRegistry.addEndpoint("/endpointSang").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端给服务端发消息的地址的前缀，是调用的时候使用Messagemapping的
        registry.setApplicationDestinationPrefixes("/ws");
        //客户端接收服务端消息的地址的前缀信息
        registry.enableSimpleBroker("/topic");
        //上面两个方法定义的信息其实是相反的，一个定义了客户端接收的地址前缀，一个定义了客户端发送地址的前缀

    }
}
