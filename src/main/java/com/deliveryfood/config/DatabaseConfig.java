package com.deliveryfood.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        var factoryBean = new SqlSessionFactoryBean();
//        var configuration = new org.apache.ibatis.session.Configuration();
//
//        configuration.setMapUnderscoreToCamelCase(true);
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setConfiguration(configuration);
//
//        var resolver = new PathMatchingResourcePatternResolver();
//        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/xml/*.xml"));
//        return factoryBean.getObject();
//    }

//    @Bean
//    public DataSource dataSource() {
//        PlatformTransactionManager dataSource = new DataSourceTransactionManager();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost/test");
//        dataSource.setUsername("root"); dataSource.setPassword("root");
//        return dataSource;
//    }
//
//    @Bean
//    public DatabaseConfiguration databaseConfig() {
//        return new DatabaseConfiguration(dataSource(), "config", "key", "value");
//    }

}