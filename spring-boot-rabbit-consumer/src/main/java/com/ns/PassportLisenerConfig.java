package com.ns;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lenovo on 2017/9/19.
 */

@Configuration
@ConfigurationProperties(prefix = "passportConsumer")
public class PassportLisenerConfig extends AbsMqListenerConfig {

    @Bean("autoMessageLisner")
    public SimpleMessageListenerContainer messageContainer() {
        return super.messageContainer();
    }

    @Override
    //  @Bean
    protected ChannelAwareMessageListener getMessageListener() {
        return new AutoMqListener();
    }
}

