package com.didekin.trash.endpoints;

import com.didekin.trash.configuration.Profiles.JksInAppClient;

import static com.didekin.trash.configuration.RetrofitConfigurationDev.jetty_local_URL;
import static com.didekin.trash.configuration.RetrofitConfigurationDev.local_jks_appclient;
import static com.didekin.trash.configuration.RetrofitConfigurationDev.local_jks_appclient_pswd;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.jetty_pre_URL;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.pre_jks_appclient;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.pre_jks_appclient_pswd;

/**
 * User: pedro@didekin
 * Date: 02/05/16
 * Time: 10:00
 */
public enum RetrofitHandlerForTests {

    JETTY_LOCAL(jetty_local_URL, new JksInAppClient(local_jks_appclient, local_jks_appclient_pswd)),
    JETTY_PRE(jetty_pre_URL, new JksInAppClient(pre_jks_appclient, pre_jks_appclient_pswd)),
    ;

    private final RetrofitHandler handler;

    RetrofitHandlerForTests(String hostPort, JksInAppClient jksInAppClient)
    {
        handler = new RetrofitHandler(hostPort, jksInAppClient);
    }

    public RetrofitHandler getHandler()
    {
        return handler;
    }
}
