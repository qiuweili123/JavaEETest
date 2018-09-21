package com.thc;

import java.util.Map;

/**
 * Created by lenovo on 2017/9/16.
 */
public interface ExchangeUtil {
    enum ExchangEnum {
        DIRECT {
            @Override
            public Exchange createExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> args) {
                return new DirectExchange(name, durable, autoDelete, args);
            }
        }, FANOUT {
            @Override
            public Exchange createExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> args) {
                return new FanoutExchange(name, durable, autoDelete, args);
            }
        }, TOPIC {
            @Override
            public Exchange createExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> args) {
                return new TopicExchange(name, durable, autoDelete, args);
            }
        },
        DELAYED {
            @Override
            public Exchange createExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> args) {
                return new CustomExchange(name, "delayed", durable, autoDelete, args);
            }
        };

        public abstract Exchange createExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> args);
    }
}