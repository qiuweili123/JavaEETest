package com.ns.springboothikaricp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebApplication  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("----tomcat strarting---");
        return builder.sources(Application.class);
    }
}
