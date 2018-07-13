package com.ns.springboothikaricp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

@MapperScan("com.ns.*.dao")

//@EnableAspectJAutoProxy

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
