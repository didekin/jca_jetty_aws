package com.didekin.trash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static com.didekin.trash.configuration.Profiles.DB_PRE;

/**
 * User: pedro@didekin
 * Date: 05/11/15
 * Time: 15:59
 */
@Configuration
@ContextConfiguration(classes = {DataSourceCommonConfig.class})
public class DataSourceConfigurationPre {

    @Autowired
    org.apache.tomcat.jdbc.pool.DataSource tomcatDs;

    @Profile(DB_PRE)
    @Bean
    public DataSource dataSource()
    {
        tomcatDs.setUrl("jdbc:mysql://mysql-frank-1.c2ojt9azfyy4.eu-central-1.rds.amazonaws.com:3306/didekin_pre?user=frank_1_root&password=xAt-WDS-7sT-YSb");
        return tomcatDs;
    }
}
