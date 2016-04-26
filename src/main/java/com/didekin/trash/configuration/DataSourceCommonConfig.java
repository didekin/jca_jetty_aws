package com.didekin.trash.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: pedro@didekin
 * Date: 26/04/16
 * Time: 15:06
 */
@Configuration
@PropertySource({"classpath:/application.properties"})
public class DataSourceCommonConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource()
    {
        DataSource ds = new DataSource();
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setInitialSize(env.getProperty("spring.datasource.initial_size", Integer.class));
        ds.setMaxActive(env.getProperty("spring.datasource.maxactive", Integer.class));
        ds.setMaxIdle(env.getProperty("spring.datasource.maxidle", Integer.class));
        ds.setMinIdle(env.getProperty("spring.datasource.minidle", Integer.class));
        ds.setValidationQuery(env.getProperty("spring.datasource.validation_query"));
        return ds;
    }
}
