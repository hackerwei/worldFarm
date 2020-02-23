package com.hz.world.core.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @outhor lujian
 * @create 2018-05-24 16:56
 */
@Configuration
public class CoreDBConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public SqlSessionFactory coreSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:com/hz/world/core/**/*Mapper.xml"));
        sqlSessionFactoryBean.setDataSource(coreDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public static MapperScannerConfigurer coreMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hz.world.core.**.dao.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("coreSqlSessionFactory");
        return mapperScannerConfigurer;
    }

    public DataSource coreDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(environment.getProperty("mysql.coreDB.username"));
        druidDataSource.setPassword(environment.getProperty("mysql.coreDB.password"));
        druidDataSource.setUrl(environment.getProperty("mysql.coreDB.url"));
        druidDataSource.setConnectionInitSqls(Lists.newArrayList("set names utf8mb4"));
        return druidDataSource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
