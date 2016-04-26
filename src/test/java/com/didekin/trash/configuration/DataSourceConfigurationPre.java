package com.didekin.trash.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.didekin.trash.configuration.Profiles.DB_PRE;

/**
 * User: pedro@didekin
 * Date: 05/11/15
 * Time: 15:59
 */
@Configuration
@PropertySource({"classpath:/application.properties"})
public class DataSourceConfigurationPre {

    @Autowired
    Environment env;

    @Profile(DB_PRE)
    @Bean
    public DataSource dataSource()
    {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl("jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME"));
        ds.setUsername(System.getenv("RDS_USERNAME"));
        ds.setPassword(System.getenv("RDS_PASSWORD"));

//        ds.setUrl("jdbc:mysql://mysql-frank-1.c2ojt9azfyy4.eu-central-1.rds.amazonaws.com:3306/didekin_pre?user=frank_1_root&password=xAt-WDS-7sT-YSb");

        ds.setInitialSize(env.getProperty("spring.datasource.initial_size", Integer.class));
        ds.setMaxActive(env.getProperty("spring.datasource.maxactive", Integer.class));
        ds.setMaxIdle(env.getProperty("spring.datasource.maxidle", Integer.class));
        ds.setMinIdle(env.getProperty("spring.datasource.minidle", Integer.class));
        ds.setValidationQuery(env.getProperty("spring.datasource.validation_query"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
