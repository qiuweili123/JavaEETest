package com.ns;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by lenovo on 2017/11/13.
 */
public abstract class AbsMqListener<T> implements ChannelAwareMessageListener {
    private T entity;

    public AbsMqListener() {
        entity = (T) GenericsUtils.getSuperClassGenricType(getClass());
    }

    @Override
    public void onMessage(Message message, Channel channel) throws  Exception {
        //业务处理，放到action层，并返回处理成功还是异常的flag
        String msg = new String(message.getBody(), "UTF-8");
       // T obj = mapper.readValue(msg, (Class<T>) entity.getClass());
        Class<T> clazz = (Class<T>) entity.getClass();
        T obj= JSON.parseObject(msg, (Class<T>) entity);
        handerMessage(obj,message,channel);
    }


    public abstract void handerMessage(T obj, Message message, Channel channel) ;

}
