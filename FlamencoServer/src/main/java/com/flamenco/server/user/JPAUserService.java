package com.flamenco.server.user;

import com.flamenco.server.domain.Greeting;
import com.flamenco.server.domain.UserService;

import javax.inject.Inject;

public class JPAUserService implements UserService {

    private final Greeting greeting;

    @Inject
    public JPAUserService(Greeting greeting) {
        this.greeting = greeting;
    }
}
