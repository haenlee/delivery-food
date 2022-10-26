package com.deliveryfood.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@MapperScan(basePackages = "com.deliveryfood.mapper")
public class DataSourceConfig {

    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(@Qualifier(value = "masterDataSource") DataSource masterDataSource,
                                        @Qualifier(value = "slaveDataSource") DataSource slaveDataSource) {

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        Map<Object, Object> targetDataSourcesMap = new HashMap<>();

        targetDataSourcesMap.put("master",masterDataSource);
        targetDataSourcesMap.put("slave", slaveDataSource);

        routingDataSource.setTargetDataSources(targetDataSourcesMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;

//        Map<Object, Object> targetDataSourcesMap = new HashMap<>();
//        targetDataSourcesMap.put("master",masterDataSource);
//        targetDataSourcesMap.put("slave", slaveDataSource);
//
//        return new ReplicationRoutingDataSource(targetDataSourcesMap);
    }

    @Bean(name = "proxyDataSource")
    public DataSource proxyDataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier(value = "proxyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    private final ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSession(DataSource dataSource) throws Exception {
        log.info("###### TESTLOG sqlSession");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        log.info("###### TESTLOG sqlSessionTemplate");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier(value = "proxyDataSource") DataSource dataSource) {
//        log.info("###### TESTLOG transactionManager");
//        return new DataSourceTransactionManager(dataSource);
//    }

}
