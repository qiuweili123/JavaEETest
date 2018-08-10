package com.ns.springboothikaricp.config;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocetMqConfig {



    /*@Bean
    public DefaultMQProducer mqProducer(){
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
     //   producer.setNamesrvAddr("192.168.19.128:9876");
        producer.setNamesrvAddr("192.168.19.128:9876");
        //producer.setVipChannelEnabled(false);
        return  producer;
    }
    @Bean
    public RocketMQTemplate rocketMQTemplate(){
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();

        rocketMQTemplate.setProducer(mqProducer());
        return rocketMQTemplate;
    }*/

   /* //  @Bean("rocketMqTemplate")
    public RocketMQProducerTemplate rocketMQProducerTemplate() {
        RocketMQProducerTemplate template = new RocketMQProducerTemplate();
        template.setNamesrvAddr("192.168.19.128:9876");
        template.setProducerGroup("test01");

        return template;
    }*/
}
