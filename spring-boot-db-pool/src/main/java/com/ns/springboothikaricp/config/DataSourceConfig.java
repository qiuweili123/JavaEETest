package com.ns.springboothikaricp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 数据源配置
 */
@Configuration
//@EnableScheduling
@Component
//@ConfigurationProperties(prefix = "hikari")
public class DataSourceConfig {

    @Autowired
    private DBProperties properties;

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

    @ConfigurationProperties(prefix = "hikari.test1")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }


    @Bean
    public HikariDataSource hikariDataSource() {
  HikariConfig config=hikariConfig();
        return new HikariDataSource(hikariConfig());
    }

   /*
    //此种方法实现的是数据库连接的延迟加载效果
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("test1", properties.getTest1());
        targetDataSources.put("test2", properties.getTest2());
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(properties.getTest1());
        return dataSource;
    }*/




   /* @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/

}
