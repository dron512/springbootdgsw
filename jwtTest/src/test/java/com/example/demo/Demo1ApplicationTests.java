package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

@SpringBootTest
class Demo1ApplicationTests {
    private static AntPathMatcher antPathMatcher;
    @Test
    void contextLoads() {
        antPathMatcher = new AntPathMatcher("/aa/**");
        System.out.println(antPathMatcher.equals("/aa/bb"));
        System.out.println(antPathMatcher.equals("/aa/bb/"));
        System.out.println(antPathMatcher.equals("/aa/bb/cc"));
        System.out.println(antPathMatcher.equals("/aa/bb/cc/dd"));
    }

    public static void main(String[] args){
        antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/aa/**","/aa/bb"));
        System.out.println(antPathMatcher.match("/aa/**","/aa/bb/"));
        System.out.println(antPathMatcher.match("/aa/**","/aa/bb/cc"));
        System.out.println(antPathMatcher.match("/aa/**","/aa/bb/cc/dd"));
    }

}
