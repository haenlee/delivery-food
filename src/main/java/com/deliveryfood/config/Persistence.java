package com.deliveryfood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@PropertySource("application.properties")
public class Persistence {
    @Autowired
    Environment env;

    @Bean
    DataSource dataSource() {
        return new DriverManagerDataSource(
            env.getProperty("spring.datasource.url"),
            env.getProperty("spring.datasource.username"),
            env.getProperty("spring.datasource.driver")
        );
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}