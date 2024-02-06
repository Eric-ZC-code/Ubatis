package com.middleware.ubatis.buider;

import com.middleware.ubatis.session.Configuration;
import com.middleware.ubatis.type.TypeAliasRegistry;

public class BaseBuilder {
    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;
    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
