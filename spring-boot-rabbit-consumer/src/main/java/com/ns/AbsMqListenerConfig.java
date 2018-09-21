package com.ns;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2017/9/19.
 */
//@Configuration
public abstract class AbsMqListenerConfig extends AbsNsMqConfig implements RabbitListenerConfigurer {
    @Resource
    private ConnectionFactory connectionFactory;

    private String queueName;

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }


/*
    @Resource
    private RabbitAdmin rabbitAdmin;*/

  /*  public AbsMqListenerConfig(ConnectionFactory connectionFactory, RabbitAdmin rabbitAdmin) {
        this.connectionFactory = connectionFactory;
        this.rabbitAdmin=rabbitAdmin;
    }*/






 /*   public Exchange exchange() {
        Exchange exchange = getExchange();
     //   rabbitAdmin.declareExchange(exchange);
        return exchange;
    }*/

    @Bean
    public Queue queue() {
        Queue queue = new Queue(queueName);
        //   amqpAdmin().declareQueue(queue);
        return queue;
    }
    /*public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory);
    }*/

    @Bean
    public Binding binding() {
        Binding binding = ExchangeUtil.ExchangEnum.valueOf(getExchangeType().toUpperCase()).bindingExchange(queue(), getExchange(), getRoutingKey());
        //  rabbitAdmin.declareBinding(binding);
        return binding;
    }

    // @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        // System.out.println(connectionFactory);
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    public SimpleMessageListenerContainer messageContainer() {
        return messageContainer(AcknowledgeMode.AUTO);
    }

    //  @Bean
    public SimpleMessageListenerContainer messageContainer(AcknowledgeMode acknowledgeMode) {
        return messageContainer(connectionFactory, acknowledgeMode);
    }

    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory, AcknowledgeMode acknowledgeMode) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(10);
        container.setConcurrentConsumers(3);
        container.setAcknowledgeMode(acknowledgeMode);
        container.setMessageListener(getMessageListener());
        return container;
    }


    protected abstract ChannelAwareMessageListener getMessageListener();
}
