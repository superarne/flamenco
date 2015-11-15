package com.flamenco.server.config;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Jetty {

    private static final Logger log = LoggerFactory.getLogger(Jetty.class);

    private final Server server;
    private final int port;

    public Jetty(int port) {

        this.port = port;
        server = new Server(port);
        ServletContextHandler servletHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);

        servletHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        GuiceServletConfig contextListener = new GuiceServletConfig();
        servletHandler.addEventListener(contextListener);
        servletHandler.addServlet(HttpServletDispatcher.class, "/*");
    }

    public int getPort() {
        return port;
    }

    public void start() throws Exception {

        server.start();
        log.debug("jetty started on port: {}", port);
    }

    public void stop() throws Exception {
        server.stop();
        log.debug("jetty stopped");
    }
}
