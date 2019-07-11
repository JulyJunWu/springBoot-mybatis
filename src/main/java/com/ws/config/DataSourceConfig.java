package com.ws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    public static int readSize;

    @Bean
    @ConfigurationProperties(prefix = "config.master")
    public DataSource masterDatasource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "config.slave2")
    public DataSource slave2Datasource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "config.slave3")
    public DataSource salve3Datasource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    //事务管理
    //@Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(CustomerRoutingDataSource customerRoutingDataSource) {
        return new DataSourceTransactionManager(customerRoutingDataSource);
    }

    @Value("${config.readSize}")
    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }
}
