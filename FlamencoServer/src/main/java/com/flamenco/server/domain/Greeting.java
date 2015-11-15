package com.flamenco.server.domain;

import com.google.common.base.Objects;

public class Greeting {

    public Greeting() {

    }

    public Greeting(String value) {
        this.value = value;
    }

    public String value;

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}
