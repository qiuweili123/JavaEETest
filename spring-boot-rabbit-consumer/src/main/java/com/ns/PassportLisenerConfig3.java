package com.ns;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lenovo on 2017/9/19.
 */

@Configuration
@ConfigurationProperties(prefix = "passportConsumer3")
public class PassportLisenerConfig3 extends AbsMqListenerConfig {

    @Override
    @Bean("test3")
    public Queue queue() {
        return super.queue();
    }

    @Override
    @Bean("bing3")
    public Binding binding() {
        return super.binding();
    }

    //只有建立不同的实例才能建立不同队列的消费
    @Bean("mesn1")
    //使用@RabbitListener必须使用对象接收，不建议手动ack
    public SimpleMessageListenerContainer messageContainer() {
        return super.messageContainer(AcknowledgeMode.MANUAL);
    }

    @Override
    //@Bean
    protected ChannelAwareMessageListener getMessageListener() {
        return new ManuListener();
    }
}

