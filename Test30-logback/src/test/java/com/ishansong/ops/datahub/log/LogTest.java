package com.ishansong.ops.datahub.log;

import com.ishansong.ops.datahub.ApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


@Slf4j
public class LogTest extends ApplicationTests {
    private Logger risk = LoggerFactory.getLogger("risk");
    private Logger risk2 = LoggerFactory.getLogger("risk2");
    TrivialConfigurator trivialConfigurator;

    static {
        System.setProperty("org.springframework.boot.logging.LoggingSystem","org.springframework.boot.logging.logback.LogbackExtLoggingSystem");
    }

    //  LoggerFactory.getLogger("risk");
    @Test
    public void testLog1() {
        System.out.println("trivialConfigurator==" + (trivialConfigurator == null));
        risk.info("hello wo:{}", "weee");
        risk2.info("hello2 wo:{}", "66666");
        // log.info("common...........");

    /*    CustomProperty p1=new CustomProperty("t1");
        log.info("--------t1-----");
        CustomProperty p2=new CustomProperty("t2");
        log.info("--------t2-----");*/
        // log.info("hello wo:{}", "zhansan");
        String counterName = "order";

        MDC.put("logFileName", counterName);
        log.info("start counter {}", counterName);
        MDC.remove("logFileName");

    }

}
