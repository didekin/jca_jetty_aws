package com.didekin.trash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static com.didekin.trash.configuration.Profiles.DB_LOCAL;
import static com.didekin.trash.configuration.Profiles.JETTY_LOCAL;

/**
 * User: pedro@didekin
 * Date: 05/11/15
 * Time: 15:59
 */
@Configuration
@PropertySource({"classpath:/application.properties"})
public class DataSourceConfigurationDev {

    @Autowired
    Environment env;

    @Profile({DB_LOCAL, JETTY_LOCAL})
    @Bean
    public DataSource dataSource()
    {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(Profiles.Uris.db_local_URL);
        ds.setUsername("pedro");
        ds.setPassword("pedro");
        ds.setInitialSize(env.getProperty("spring.datasource.initial_size", Integer.class));
        ds.setMaxActive(env.getProperty("spring.datasource.maxactive", Integer.class));
        ds.setMaxIdle(env.getProperty("spring.datasource.maxidle", Integer.class));
        ds.setMinIdle(env.getProperty("spring.datasource.minidle", Integer.class));
        ds.setValidationQuery(env.getProperty("spring.datasource.validation_query"));
        return ds;
    }
}
