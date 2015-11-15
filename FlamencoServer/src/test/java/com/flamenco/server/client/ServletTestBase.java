package com.flamenco.server.client;

import java.io.IOException;
import java.net.ServerSocket;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.flamenco.server.config.Jetty;

import retrofit.Retrofit;

public abstract class ServletTestBase {

    private Jetty jetty;
    private Retrofit retrofit;

    @BeforeClass
    public void setUp() throws Exception {

        jetty = new Jetty(findFreePort());
        jetty.start();

        retrofit = WebClient.get("http://localhost:" + jetty.getPort());
    }

    @AfterClass
    public void tearDown() throws Exception {

        jetty.stop();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static int findFreePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            return socket.getLocalPort();
        }
        catch (IOException e) {
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                }
            }
        }
        return -1;
    }
}
