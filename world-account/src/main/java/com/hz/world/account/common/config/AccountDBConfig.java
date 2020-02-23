package com.hz.world.account.common.config;

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
public class AccountDBConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public SqlSessionFactory accountSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:com/hz/world/account/**/*Mapper.xml"));
        sqlSessionFactoryBean.setDataSource(accountDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public static MapperScannerConfigurer accountMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hz.world.account.**.dao.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("accountSqlSessionFactory");
        return mapperScannerConfigurer;
    }

    public DataSource accountDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(environment.getProperty("mysql.accountDB.username"));
        druidDataSource.setPassword(environment.getProperty("mysql.accountDB.password"));
        druidDataSource.setUrl(environment.getProperty("mysql.accountDB.url"));
        druidDataSource.setConnectionInitSqls(Lists.newArrayList("set names utf8mb4"));
        return druidDataSource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
