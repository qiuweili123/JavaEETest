package com.ns;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * Created by lenovo on 2017/11/13.
 */
public abstract class AbsManuAckMqListener<T> extends AbsMqListener<T> {

    @Override
    public void handerMessage(T obj, Message message, Channel channel) {
        boolean flag = processingMessage(obj);
        try {
            if (flag) {
                basicACK(obj, message, channel);
            } else {
                basicNACK(obj, message, channel);
            }
        } catch (IOException e) {
            System.out.println("throw IOException");
        }
    }

    //正常消费掉后通知mq服务器移除此条mq
    private void basicACK(T obj, Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        processingSuccess(obj, message);
    }

    //处理异常，mq重回队列
    private void basicNACK(T obj, Message message, Channel channel) throws IOException {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        processingFail(obj, message);
    }

    public abstract boolean processingMessage(T obj);

    public abstract boolean processingFail(T obj, Message message);

    public abstract boolean processingSuccess(T obj, Message message);
}
