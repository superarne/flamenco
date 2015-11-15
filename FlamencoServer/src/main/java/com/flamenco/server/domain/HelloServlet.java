package com.flamenco.server.domain;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/hello")
public class HelloServlet {

    private static final Logger log = LoggerFactory.getLogger(HelloServlet.class);

    @GET
    @Path("/greeting")
    @Produces("application/json")
    public Greeting sayHi() {

        log.debug("received greeting request");
        return new Greeting("lala");
    }
}
