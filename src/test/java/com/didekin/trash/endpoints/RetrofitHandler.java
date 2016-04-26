package com.didekin.trash.endpoints;

import com.didekin.trash.configuration.Profiles.JksInAppClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.didekin.trash.configuration.RetrofitConfigurationDev.jetty_local_URL;
import static com.didekin.trash.configuration.RetrofitConfigurationDev.local_jks_appclient;
import static com.didekin.trash.configuration.RetrofitConfigurationDev.local_jks_appclient_pswd;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.jetty_pre_URL;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.pre_jks_appclient_Uri;
import static com.didekin.trash.configuration.RetrofitConfigurationPre.pre_jks_appclient_pswd;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 10/04/16
 * Time: 12:22
 */
public enum RetrofitHandler {

    JETTY_LOCAL(jetty_local_URL, new JksInAppClient(local_jks_appclient, local_jks_appclient_pswd)),
    JETTY_PRE(jetty_pre_URL, new JksInAppClient(pre_jks_appclient_Uri, pre_jks_appclient_pswd)),
    ;

    private final Retrofit retrofit;

    RetrofitHandler(final String hostPort, final JksInAppClient JksInAppClient)
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(hostPort)
                .client(getOkHttpClient(JksInAppClient))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T getServiceDebug(Class<T> endPointInterface)
    {
        return retrofit.create(endPointInterface);
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }

    // ====================== HELPER METHODS ========================

    OkHttpClient getOkHttpClient(JksInAppClient JksInAppClient)
    {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(doLoggingInterceptor())
                .connectTimeout(30, SECONDS)
                .readTimeout(15, MINUTES)
                .sslSocketFactory(getSslContext(JksInAppClient).getSocketFactory())
                .build();
    }

    private Interceptor doLoggingInterceptor()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BODY);
        return loggingInterceptor;
    }

    private SSLContext getSslContext(JksInAppClient JksInAppClient)
    {
        KeyStore keyStore;
        FileInputStream in;
        TrustManagerFactory tmf;
        SSLContext context = null;

        try {
            // Configuración cliente.
            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            in = new FileInputStream(JksInAppClient.jksUri);
            keyStore.load(in, JksInAppClient.jksPswd.toCharArray());

            // Create a TrustManager that trusts the CAs in our JksInAppClient
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return context;
    }
}
