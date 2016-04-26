package com.didekin.trash.endpoints;

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

import static com.didekin.trash.endpoints.JceEndPoints.HOST_PORT;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 10/04/16
 * Time: 12:22
 */
enum RetrofitHandler {

    HANDLER,;

    final Retrofit retrofit;

    RetrofitHandler()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST_PORT)
                .client(getOkHttpClient())
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

    OkHttpClient getOkHttpClient()
    {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(doLoggingInterceptor())
                .connectTimeout(30, SECONDS)
                .readTimeout(15, MINUTES)
                .sslSocketFactory(getSslContext().getSocketFactory())
                .build();
    }

    private Interceptor doLoggingInterceptor()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BODY);
        return loggingInterceptor;
    }

    private SSLContext getSslContext()
    {
        KeyStore keyStore;
        FileInputStream in;
        TrustManagerFactory tmf;
        SSLContext context = null;

        try {
            // Configuración cliente.
            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            in = new FileInputStream("/usr/local/keystores/jetty1.jks");
            keyStore.load(in, "jca_springboot_jetty".toCharArray());

            // Create a TrustManager that trusts the CAs in our KeyStore
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
