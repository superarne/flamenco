package com.flamenco.server.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flamenco.server.domain.Greeting;

import retrofit.Call;
import retrofit.Response;

public class HelloServiceTest extends ServletTestBase {

    private HelloService helloService;

    @BeforeClass
    @Override
    public void setUp() throws Exception {
        super.setUp();
        helloService = getRetrofit().create(HelloService.class);
    }

    @Test
    public void testSayHi() throws IOException {
        Call<Greeting> greetingCall = helloService.sayHi();
        Response<Greeting> execute = greetingCall.execute();
        assertThat(execute.code()).isEqualTo(200);
        assertThat(execute.body().value).isEqualTo("lala");
    }

}