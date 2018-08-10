package com.ns.springboothikaricp.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserNameKeyExpireListener extends KeyspaceEventMessageListener {

    private static final Topic KEYEVENT_EXPIRED_TOPIC = new PatternTopic("__key*@0__:expired");

    @Resource
    private StringRedisSerializer stringRedisSerializer;

    public UserNameKeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
        super.setKeyspaceNotificationsConfigParameter("Ex");
    }


    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, KEYEVENT_EXPIRED_TOPIC);
    }


    @Override
    protected void doHandleMessage(Message message) {

        String key = stringRedisSerializer.deserialize(message.getBody());
        System.out.println("##before value##");
        System.out.println("key:::" + key);
    }
}
