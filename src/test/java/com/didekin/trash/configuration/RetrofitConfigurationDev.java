package com.didekin.trash.configuration;

import com.didekin.trash.endpoints.RetrofitHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.didekin.trash.Application.JETTY_host_name;
import static com.didekin.trash.configuration.Profiles.JETTY_LOCAL;

/**
 * User: pedro@didekin
 * Date: 20/04/16
 * Time: 16:33
 */
@SuppressWarnings("WeakerAccess")
@Profile(JETTY_LOCAL)
@Configuration
public class RetrofitConfigurationDev {

    public static final int NGINX_port = 8443;
    public static final String jetty_local_URL = "https://" + JETTY_host_name + ":" + NGINX_port;
    public static final String local_jks_appclient = "/Users/pedro/keystores/jca_jetty";
    public static final String local_jks_appclient_pswd = "jca_jetty";

    @Bean
    public RetrofitHandler retrofitHandler() throws NoSuchFieldException, IllegalAccessException
    {
        return RetrofitHandler.JETTY_LOCAL;
    }

//  ================================== HELPER METHODS ===================================


    /*static void setEnvironment() throws NoSuchFieldException, IllegalAccessException
    {
        Map<String, String> newEnv = new HashMap<>();
        newEnv.put(ENV_JKS_NAME, "jca_jetty");
        newEnv.put(ENV_JKS_PSWD, "jca_jetty");

        Class[] classes = Collections.class.getDeclaredClasses();
        Map<String, String> env = System.getenv();
        for (Class cl : classes) {
            if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                Field field = cl.getDeclaredField("m");
                field.setAccessible(true);
                Object obj = field.get(env);
                Map<String, String> map = (Map<String, String>) obj;
                map.clear();
                map.putAll(newEnv);
            }
        }
//        System.setProperty("javax.net.debug", "ssl:handshake");
    }*/
}
