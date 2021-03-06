package com.thoughtworks.i1.commons.server;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.thoughtworks.i1.commons.config.HttpConfiguration;

public abstract class Embedded {
    public abstract Embedded addServletContext(String contextPath, boolean shareNothing, final Module... modules);

    public abstract Embedded start(boolean standalone);

    public abstract Embedded stop();

    public static Embedded jetty(HttpConfiguration configuration) {
        return new EmbeddedJetty(configuration);
    }

    public abstract Injector injector();
}
