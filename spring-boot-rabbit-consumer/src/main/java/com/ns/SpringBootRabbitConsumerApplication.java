package com.ns;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication
 //@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class SpringBootRabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitConsumerApplication.class, args);
    }
}
