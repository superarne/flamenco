package com.flamenco.server.client;

import javax.ws.rs.Produces;

import com.flamenco.server.user.User;

import retrofit.Call;
import retrofit.http.GET;

public interface UserService {

    @GET("/user/all")
    @Produces("application/json")
    Call<User> getUsers();
    
}
