package com.didekin.trash.endpoints;

import com.didekin.trash.dominio.Greeting;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * User: pedro@didekin
 * Date: 07/04/16
 * Time: 17:20
 */
public interface JceEndPoints {

    @GET(GreetingController.GREETING_URL)
    Call<Greeting> greeting(@Query(GreetingController.NAME_PARAM) String name);

    @GET(GreetingController.GREETING_URL_CLOSE)
    Call<Greeting> greetingClose(@Query(GreetingController.NAME_PARAM) String name);
}
