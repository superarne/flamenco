package com.flamenco.server.config;

import com.flamenco.server.domain.HelloServlet;
import com.flamenco.server.user.UserServlet;
import com.google.inject.AbstractModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class GuiceServletConfig extends GuiceResteasyBootstrapServletContextListener {

    public GuiceServletConfig() {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected List getModules(final ServletContext context) {
        final List result = new ArrayList();
        result.add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(HelloServlet.class);
                bind(UserServlet.class);
            }
        });
        return result;
    }
}
