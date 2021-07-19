package com.backendStudy.cat.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //다음의 prefix로 시작하는 설정을 이용해서 hikariCP의 설정 파일을 만듦
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() { // 위에서 만든 설정 파일을 이용해서 디비와 연결하는 데이터 소스를 생성
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception { //DataSource를 참조하여 MyBatis와 Mysql 서버를 연동시켜준다.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		//Mapper 파일 위치를 설정. [classpath]: resource 폴더 의미, [/mapper/**/]: mapp 폴더 밑의 모든 폴더를 의미, [*Mapper.xml]: 이름이 Mapper로 끝나고 확장자가 xml인 모든 파일을 의미
        factoryBean.setConfiguration(mybatisConfig()); //Mybatis의 설정파일의 위치를 참조
        return factoryBean.getObject();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
