package com.ns;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * Created by lenovo on 2017/9/19.
 */

//@Configuration
//@ConfigurationProperties(prefix = "passportConsumer")
public class PassportLisenerConfig2 {


    //  @ConfigurationProperties(prefix="spring.rabbitmq1")
    //@Bean("connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        //防止信道缓存不够造成消息丢失，官方推荐100可完全避免此丢失消息情况
        // connectionFactory.setChannelCacheSize(1000);
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        //开启确认机制，可监听消息是否到达交换机
        //     connectionFactory.setPublisherConfirms(rabbitmqProps.isPublisherConfirms());
        //mandatory，不可路由时回调
        //     connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    // @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
//    @ConfigurationProperties(prefix="spring.rabbitmq2")
//    @Bean
  /*  public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        //防止信道缓存不够造成消息丢失，官方推荐100可完全避免此丢失消息情况
        // connectionFactory.setChannelCacheSize(1000);
   *//*      connectionFactory.setAddresses("127.0.0.1:25672");
       connectionFactory.setUsername("guest");
      connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");*//*
        //开启确认机制，可监听消息是否到达交换机
        //     connectionFactory.setPublisherConfirms(rabbitmqProps.isPublisherConfirms());
        //mandatory，不可路由时回调
        //     connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }
//    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }*/
}

