package com.dgsw.ex01_springboot.config;

import com.dgsw.ex01_springboot.components.B;
import com.dgsw.ex01_springboot.components.C;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MyConfig {

    @Bean
    public B b(){
        return  new B();
    }

    @Bean
    public C c(){
        return  new C();
    }



    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername("root");
        ds.setPassword("1234");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springboot");
        return ds;
    }

}
