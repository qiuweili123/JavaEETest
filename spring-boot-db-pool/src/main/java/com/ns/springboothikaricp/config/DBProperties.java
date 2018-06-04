package com.ns.springboothikaricp.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 实际数据源配置
 */
@Component
@ConfigurationProperties(prefix = "hikari")
public class DBProperties {
    private HikariConfig test1;
    private HikariConfig test2;

    public HikariConfig getTest1() {
        return test1;
    }

    public void setTest1(HikariConfig test1) {
        this.test1 = test1;
    }

    public HikariConfig getTest2() {
        return test2;
    }

    public void setTest2(HikariConfig test2) {
        this.test2 = test2;
    }
}
