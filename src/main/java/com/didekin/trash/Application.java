package com.didekin.trash;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static final String JETTY_host_name = "localhost";
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @EnableWebSecurity
    public static class WebServerConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception
        {
            http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/error", "/**").permitAll()
                    .and()
                    .formLogin().disable().httpBasic()
//                    .and()
//                    .requiresChannel()
//                    .antMatchers("/**")
//                    .requiresSecure()
            ;
        }
    }

//    ========================= HELPER METHODS ===========================
}
