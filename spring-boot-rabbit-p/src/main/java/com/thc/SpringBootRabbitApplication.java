package com.thc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * 当使用spring-boot-configuration-processor库时会默认打开RabbitAutoConfiguration,它会自动创建一个 CachingConnectionFactory对象，
 * 一个RabbitTemplate对象，一个AmqpAdmin对象以及一个RabbitMessageTemplate对象
 * 如果我们需要创建我们自定义的从connnetfactory，就需要禁用
 */
//@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class SpringBootRabbitApplication {



   /* @Bean(name = "rabbitTemplate")
   RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
         return  rabbitTemplate;
    }
*/



/*

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitApplication.class, args);
    }

}
