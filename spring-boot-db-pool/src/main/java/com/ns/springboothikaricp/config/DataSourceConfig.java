package com.ns.springboothikaricp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 */
@Configuration
//@EnableScheduling
@ConfigurationProperties(prefix = "hikari")
public class DataSourceConfig {

    @Autowired//此种方式是注入属性，不会创建连接，而new HikariConfig（）或者new HikariDataSource()都会创建出连接
    private DBProperties properties;

    private HikariConfig test1;

    // private HikariConfig test2;

    public HikariConfig getTest1() {
        return test1;
    }

    public void setTest1(HikariConfig test1) {
        this.test1 = test1;
    }
/*
    public HikariConfig getTest2() {
        return test2;
    }

    public void setTest2(HikariConfig test2) {
        this.test2 = test2;
    }*/
    /* @Bean
    @ConfigurationProperties(prefix = "hikari.test1")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    @Bean
    @ConfigurationProperties(prefix = "hikari.test2")
    public HikariConfig hikariConfig2() {
        return new HikariConfig();
    }
*/

    //以SpringBootAppliacation启动的时候可以用两个dataSource，但是用tomcat容器启动无法启动成功
    //@Bean("test1")
  /* public HikariDataSource hikariDataSource() {

        return new HikariDataSource(hikariConfig());
    }

    //@Bean("test2")
    public HikariDataSource hikariDataSource2() {

        return new HikariDataSource(hikariConfig2());
    }*/


    //此种方法实现的是数据库连接的延迟加载效果
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        System.out.println("-----init----");
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();
        HikariDataSource hikariDataSource = new HikariDataSource(test1);
        /*targetDataSources.put("test1",hikariDataSource());
        targetDataSources.put("test2", hikariDataSource2());*/
        targetDataSources.put("test1", hikariDataSource);
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(hikariDataSource);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
