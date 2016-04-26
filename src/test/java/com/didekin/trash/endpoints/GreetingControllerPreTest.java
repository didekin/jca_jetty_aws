package com.didekin.trash.endpoints;

import com.didekin.trash.Application;
import com.didekin.trash.HttpConfiguration;
import com.didekin.trash.dominio.Greeting;
import com.didekin.trash.dominio.ErrorBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import static com.didekin.trash.endpoints.RetrofitHandler.HANDLER;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 07/04/16
 * Time: 17:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest
public class GreetingControllerPreTest {

    private static final JceEndPoints ENDPOINT = HANDLER.getServiceDebug(JceEndPoints.class);

    @Autowired
    private HttpConfiguration httpConfiguration;

    @Test
    public void testHttpConfiguration(){
        assertThat(httpConfiguration.getSecuredPort(), is(8443));
        assertThat(httpConfiguration.getSessionTimeOut(), is(600));
    }

    @Test
    public void testGreeting() throws Exception
    {
        Response<Greeting> response = ENDPOINT.greeting("Pedro").execute();
        Greeting greeting = response.body();
        assertThat(greeting.getContent(), is("Hello, Pedro!"));

        Converter<ResponseBody, ErrorBean> converter = HANDLER.getRetrofit().responseBodyConverter(ErrorBean.class, new Annotation[0]);
        assertThat(converter, notNullValue());
    }

    @Test
    public void testGreetingClose() throws Exception
    {
        Response<Greeting> response = ENDPOINT.greetingClose("Pedro").execute();
        Greeting greeting = response.body();
        assertThat(greeting.getContent(), is("Hello Close, Pedro!"));
    }
}