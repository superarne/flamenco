package com.flamenco.server.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flamenco.server.domain.Greeting;
import com.flamenco.server.user.User;

import retrofit.*;

public class WebClient {

    private static final Logger log = LoggerFactory.getLogger(WebClient.class);

    public static Retrofit get(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static void main(String[] args) throws IOException {
    	
//    	HelloService helloService = get("http://localhost:8080").create(HelloService.class);
//        Call<Greeting> helloStringCall = helloService.sayHi();
//        log.debug("Users: " + helloStringCall.execute().body());

        UserService service = get("http://localhost:8080").create(UserService.class);

        Call<Void> user = service.createUser(new User("test", "lala", "arne@fileee.com"));
        Response<Void> execute = user.execute();
        log.debug(execute.message());
//                Call<User> stringCall = service.getUsers();
//        log.debug("Users: " + stringCall.execute().body());
        
    }
}
