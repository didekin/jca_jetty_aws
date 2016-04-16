package com.didekin.trash;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * User: pedro@didekin
 * Date: 13/10/15
 * Time: 15:37
 */
@Configuration
@PropertySource({"classpath:application.properties"})
public class HttpConfiguration {

    @Value("${server.port}")
    private int securedPort;
    @Value("${server.session.timeout}")
    private int sessionTimeOut;
    @Value("${server.ssl.key-store}")
    private String keyStorePath;
    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword ;
    /*@Value("${server.ssl.key-password}")
    private String keyPassword;*/

    public int getSecuredPort()
    {
        return securedPort;
    }

    public int getSessionTimeOut()
    {
        return sessionTimeOut;
    }
}