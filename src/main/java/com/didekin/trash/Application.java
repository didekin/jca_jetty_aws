package com.didekin.trash;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SuppressWarnings("Convert2Lambda")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    // To be deployed as a war file.
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(Application.class);
    }

    @Profile("prod")
    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer()
    {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container)
            {
                if (container instanceof JettyEmbeddedServletContainerFactory) {
                    configureJetty((JettyEmbeddedServletContainerFactory) container);
                }
            }

            private void configureJetty(JettyEmbeddedServletContainerFactory jettyFactory)
            {
                jettyFactory.addServerCustomizers(new JettyServerCustomizer() {
                    @Override
                    public void customize(Server server)
                    {
                        ServerConnector serverConnector = new ServerConnector(server);
                        serverConnector.setPort(8080);
                        server.addConnector(serverConnector);
                    }
                });
            }
        };
    }

    @Profile("prod")
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
                    .and()
                    .requiresChannel()
                    .antMatchers("/close/**")
                    .requiresSecure()
            ;
        }
    }
}
