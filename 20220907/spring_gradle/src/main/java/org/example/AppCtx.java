package org.example;

import org.example.myclass.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.components","org.example.myclass"})
public class AppCtx {

    @Bean
    public A a(){
        return new A();
    }

}
