package org.sang.ods.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration("hiveOdsDataSourceConfig")
@MapperScan(basePackages = "org.sang.ods.mapper",sqlSessionFactoryRef = "odsSessionFactory")
public class DataSourceConfig {
    @Bean(name = "odsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.ods")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("odsSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource odsDataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setDataSource(odsDataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "odsHiveTemplate")
    public JdbcTemplate hiveTemplate(DataSource odsDataSource) {
        return new JdbcTemplate(odsDataSource);
    }


}
