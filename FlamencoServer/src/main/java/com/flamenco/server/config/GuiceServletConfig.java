package com.flamenco.server.config;

import com.flamenco.server.domain.Greeting;
import com.flamenco.server.domain.HelloServlet;
import com.flamenco.server.domain.UserService;
import com.flamenco.server.user.JPAUserService;
import com.flamenco.server.user.UserServlet;
import com.google.inject.AbstractModule;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class GuiceServletConfig extends GuiceResteasyBootstrapServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(GuiceServletConfig.class);

    public GuiceServletConfig() {
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected List getModules(final ServletContext context) {
        final List result = new ArrayList();
        result.add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(HelloServlet.class);
                bind(UserServlet.class);
                bind(UserService.class).to(JPAUserService.class);
                bind(Greeting.class).toInstance(new Greeting("lalal"));
                install(new JpaPersistModule("flamenco"));
                bind(JpaInitializer.class).asEagerSingleton();
            }
        });
        return result;
    }

    @Singleton
    public static class JpaInitializer {

        @Inject
        public JpaInitializer(final PersistService service) {
            try {

                service.start();
            }
            catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
