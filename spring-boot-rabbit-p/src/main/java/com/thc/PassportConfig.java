package com.thc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lenovo on 2017/11/8.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.passport-knock-door")
public class PassportConfig extends AbsNsMqConfig {
/*
    @Bean
    @ConfigurationProperties(prefix = "spring.rabbitmq1")
    public RabbitProperties rabbitProperties(){
        return new RabbitProperties();
    }
    @Bean("connectionFactory2")
    public ConnectionFactory getConnectionFactory2() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory =
                new com.rabbitmq.client.ConnectionFactory();

        rabbitConnectionFactory.setHost("192.168.0.1");
        rabbitConnectionFactory.setPort(rabbitProperties().getPort());
        rabbitConnectionFactory.setUsername(rabbitProperties().getUsername());
        rabbitConnectionFactory.setPassword(rabbitProperties().getPassword());
        rabbitConnectionFactory.setVirtualHost(rabbitProperties().getVirtualHost());


        CachingConnectionFactory connectionFactory2 = new CachingConnectionFactory(rabbitConnectionFactory);
        return connectionFactory2;
    }
    @Bean("connectionFactory")
    public ConnectionFactory getConnectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory =
                new com.rabbitmq.client.ConnectionFactory();

      *//*  rabbitConnectionFactory.setHost("152.168.0.1");
        rabbitConnectionFactory.setPort(rabbitProperties().getPort());*//*
        rabbitConnectionFactory.setUsername(rabbitProperties().getUsername());
        rabbitConnectionFactory.setPassword(rabbitProperties().getPassword());
        rabbitConnectionFactory.setVirtualHost(rabbitProperties().getVirtualHost());


        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
        connectionFactory.setAddresses("127.0.0.1:15662");
        return connectionFactory;
    }*/
  /*  @Bean("directExchange")
    public Exchange exchange() {
        return getExchange();
    }*/
  /*  @Override
   如果有多个连接地址哦时候需要自定义connectFactory
    @ConfigurationProperties(prefix="spring.rabbitmq1")
    @Bean("connectionFactory1")
    protected ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }*/

}
