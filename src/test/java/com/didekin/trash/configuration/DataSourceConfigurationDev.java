package com.didekin.trash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static com.didekin.trash.configuration.Profiles.JETTY_LOCAL;

/**
 * User: pedro@didekin
 * Date: 05/11/15
 * Time: 15:59
 */
@Configuration
@ContextConfiguration(classes = {DataSourceCommonConfig.class})
public class DataSourceConfigurationDev {

    @Autowired
    org.apache.tomcat.jdbc.pool.DataSource tomcatDs;

    @Profile({JETTY_LOCAL})
    @Bean
    public DataSource dataSource()
    {
        tomcatDs.setUrl("jdbc:mysql://localhost:3306/didekin");
        tomcatDs.setUsername("pedro");
        tomcatDs.setPassword("pedro");
        return tomcatDs;
    }
}
