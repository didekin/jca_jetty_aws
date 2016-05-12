package com.didekin.trash.configuration;

import com.didekin.trash.endpoints.RetrofitHandlerForTests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.didekin.trash.configuration.Profiles.AWS_PRE;

/**
 * User: pedro@didekin
 * Date: 20/04/16
 * Time: 16:33
 */
@Profile(AWS_PRE)
@Configuration
public class RetrofitConfigurationPre {

    public static final String jetty_pre_URL = "https://jca-jetty-jar-env.eu-central-1.elasticbeanstalk.com";
    public static final String pre_jks_appclient = "/Users/pedro/keystores/jca_jetty_jar";
    public static final String pre_jks_appclient_pswd = "jca_jetty_jar";

    @Bean
    public RetrofitHandlerForTests retrofitHandler() throws NoSuchFieldException, IllegalAccessException
    {
        return RetrofitHandlerForTests.JETTY_PRE;
    }

}
