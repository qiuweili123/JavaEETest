package com.ns;

import org.springframework.amqp.core.Message;

/**
 * Created by lenovo on 2017/11/14.
 */
public class ManuListener extends AbsManuAckMqListener<User> {

    @Override
    public boolean processingMessage(User obj) {
        System.out.println("## ManuListener name=" + obj.getName());
        return true;
    }

    @Override
    public boolean processingFail(User obj, Message message) {
        return false;
    }

    @Override
    public boolean processingSuccess(User obj, Message message) {
        System.out.println("manu sucess");
        return true;
    }
}
