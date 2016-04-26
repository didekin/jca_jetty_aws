package com.didekin.trash.endpoints;

import com.didekin.trash.dominio.ErrorBean;
import com.didekin.trash.dominio.Greeting;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 07/04/16
 * Time: 17:37
 */
abstract class GreetingControllerTest {

    @Autowired
    private RetrofitHandler retrofitHandler;

    private JceEndPoints endPoint;
    private Retrofit retrofit;

    @Before
    public void setUp(){
        endPoint = retrofitHandler.getServiceDebug(JceEndPoints.class);
        retrofit = retrofitHandler.getRetrofit();
    }

    public void testGreeting() throws Exception
    {
        Response<Greeting> response = endPoint.greeting("Pedro").execute();
        Greeting greeting = response.body();
        assertThat(greeting.getContent(), is("Hello, Pedro!"));

        Converter<ResponseBody, ErrorBean> converter = retrofit.responseBodyConverter(ErrorBean.class, new Annotation[0]);
        assertThat(converter, notNullValue());
    }

    public void testGreetingClose() throws Exception
    {
        Response<Greeting> response = endPoint.greetingClose("Pedro").execute();
        Greeting greeting = response.body();
        assertThat(greeting.getContent(), is("Hello Close, Pedro!"));
    }
}