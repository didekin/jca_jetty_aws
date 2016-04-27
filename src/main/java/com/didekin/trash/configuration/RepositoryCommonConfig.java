package com.didekin.trash.configuration;

import com.didekin.trash.repository.ComunidadAutonomaDao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * User: pedro@didekin
 * Date: 26/04/16
 * Time: 15:06
 */
@Configuration
@PropertySource({"classpath:/application.properties"})
public class RepositoryCommonConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource tomcatDs()
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

    @Bean
    public JdbcTemplate jdbcTemplate(javax.sql.DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ComunidadAutonomaDao comunidadAutonomaDao(JdbcTemplate jdbcTemplate)
    {
        return new ComunidadAutonomaDao(jdbcTemplate);
    }
}
