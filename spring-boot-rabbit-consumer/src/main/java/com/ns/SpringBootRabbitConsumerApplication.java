package com.ns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class SpringBootRabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitConsumerApplication.class, args);
    }
}
