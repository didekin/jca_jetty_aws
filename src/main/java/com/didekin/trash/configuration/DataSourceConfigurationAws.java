package com.didekin.trash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.didekin.trash.configuration.Profiles.AWS_PRE;
import static com.didekin.trash.configuration.Profiles.DB_PRE;

/**
 * User: pedro@didekin
 * Date: 05/11/15
 * Time: 15:59
 */
@Configuration
public class DataSourceConfigurationAws {

    @Autowired
    org.apache.tomcat.jdbc.pool.DataSource tomcatDs;

    @Profile(AWS_PRE)
    @Bean
    public DataSource dataSource()
    {
        tomcatDs.setUrl("jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME"));
        tomcatDs.setUsername(System.getenv("RDS_USERNAME"));
        tomcatDs.setPassword(System.getenv("RDS_PASSWORD"));
        return tomcatDs;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
