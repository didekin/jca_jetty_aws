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

    String HOST_PORT = "http://localhost:8080";

    String GREETING_URL = "/greeting";
    String GREETING_URL_CLOSE = "/close/greeting";
    String NAME_PARAM = "name";
    String ERROR = "/error";
    String MIME_JSON = "application/json";

    @GET(GREETING_URL)
    Call<Greeting> greeting(@Query(NAME_PARAM) String name);

    @GET(GREETING_URL_CLOSE)
    Call<Greeting> greetingClose(@Query(NAME_PARAM) String name);
}
