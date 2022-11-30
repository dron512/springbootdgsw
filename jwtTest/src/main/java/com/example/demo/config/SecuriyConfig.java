package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@EnableWebSecurity
public class SecuriyConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private JwtUtil jwtAuthenticationFilter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/user/signup").permitAll();
//                .antMatchers("/api/login").permitAll();
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout();
//        http.oauth2Login().successHandler(successHandler());    //구글로그인
//        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);  //7days

        // notes/2
        // eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ5NjA2MTIsImV4cCI6MTY2NzU1MjYxMiwic3ViIjoidXNlcjE2QHplcm9jay5vcmcifQ.4YXNCHPdGwOfQh-p3GemQMZ7yG0WjlosWcBHsuXgpe8
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter filter= new ApiLoginFilter("/api/login");
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {

        return new ApiCheckFilter("/notes/**");
    }

}
