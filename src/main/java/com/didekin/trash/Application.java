package com.didekin.trash;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication(/*exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class*/)
public class Application extends SpringBootServletInitializer {

    public static final String JETTY_host_name = "localhost";
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

//    ========================= HELPER METHODS ===========================
}
