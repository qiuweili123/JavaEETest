package com.ns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/log")
public class Hello2Controller {
    private Logger logger = LoggerFactory.getLogger(Hello2Controller.class);

    @GetMapping("/infoSmall")
    public String infoSmall( HttpServletResponse response) {
        logger.info("Info log");
        response.setHeader("Access-Control-Allow-Origin","*");
        return "info";
    }

    @GetMapping("/infoBig")
    public String infoBig() {
        logger.info("Info log");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 404900; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}
