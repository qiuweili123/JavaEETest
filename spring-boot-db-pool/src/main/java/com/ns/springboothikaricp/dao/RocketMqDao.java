package com.ns.springboothikaricp.dao;

import org.springframework.stereotype.Repository;

@Repository
public class RocketMqDao {
    // @Resource
    //private RocketMQProducerTemplate rocketMqTemplate;
   /*  @Resource
    private RocketMQTemplate rocketMQTemplate;*/

    public <T> void sendMsg(T instance) throws Exception {
        // rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
/*        Message message = new Message();


        message.setBody(JSON.toJSONString(instance).getBytes());
        message.setTags("order");
        message.setTopic("topic01");
        MessageProxy messageProxy = new MessageProxy();
        messageProxy.setMessage(message);
        rocketMqTemplate.send(messageProxy);*/
    }

}
