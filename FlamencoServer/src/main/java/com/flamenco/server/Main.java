package com.flamenco.server;

import com.flamenco.server.config.Jetty;

public class Main {

    public static void main(String[] args) throws Exception {
        Jetty jetty = new Jetty(8080);
        jetty.start();
    }
}