package com.flamenco.server.client;

import javax.ws.rs.Produces;

import com.flamenco.server.domain.Greeting;

import retrofit.Call;
import retrofit.http.GET;

public interface HelloService {

    @GET("/hello/greeting")
    @Produces("application/json")
    Call<Greeting> sayHi();

}
