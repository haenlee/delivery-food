package com.deliveryfood.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@MapperScan(
//        value = "com.deliveryfood.mapper",
        basePackages = {"com.deliveryfood.mapper"},
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSession"
)
@RequiredArgsConstructor
@EnableTransactionManagement
public class MyBatisConfig {

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSession(
            @Qualifier("dataSource") DataSource dataSource,
            ApplicationContext applicationContext
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));


//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//
//        List<String> mapperLocations = new ArrayList<>();
//        mapperLocations.add("classpath:/mybatis/**/*.xml");
//        mapperLocations.add("classpath:/mybatis/**/*.xml");
//        List<Resource> resources = new ArrayList<>();
//        if (!mapperLocations.isEmpty()) {
//            for (String mapperLocation : mapperLocations) {
//                try {
//                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
//                    resources.addAll(Arrays.asList(mappers));
//                } catch (IOException e) {
//                    log.error("Mybatis resources Get Exception Occur", e);
//                }
//            }
//        }
//        resources.toArray(new Resource[resources.size()]);


        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSession")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
