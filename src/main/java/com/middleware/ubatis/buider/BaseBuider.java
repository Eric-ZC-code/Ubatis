package com.middleware.ubatis.buider;

import com.middleware.ubatis.session.Configuration;

public class BaseBuider {
    protected final Configuration configuration;
    public BaseBuider(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
