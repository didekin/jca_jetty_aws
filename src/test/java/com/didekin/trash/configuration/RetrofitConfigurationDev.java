package com.didekin.trash.configuration;

import com.didekin.trash.endpoints.RetrofitHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.didekin.trash.configuration.Profiles.JETTY_LOCAL;
import static com.didekin.trash.configuration.Profiles.JETTY_PRE;

/**
 * User: pedro@didekin
 * Date: 20/04/16
 * Time: 16:33
 */
@Configuration
public class RetrofitConfigurationDev {

    @Profile(JETTY_LOCAL)
    @Bean
    public RetrofitHandler retrofitHandler(){
        return RetrofitHandler.JETTY_LOCAL;
    }

    @Profile(JETTY_PRE)
    @Bean
    public RetrofitHandler retrofitHandler(){
        return RetrofitHandler.JETTY_LOCAL;
    }

}
