package com.flamenco.server.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.flamenco.server.user.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface UserService {

    @GET("/user/all")
    @Produces("application/json")
    Call<User> getUsers();

    @POST("/user/create")
    @Consumes("application/json")
    Call<Void> createUser(@Body User user);
    
}
