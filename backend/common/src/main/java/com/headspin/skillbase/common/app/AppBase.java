package com.headspin.skillbase.common.app;

import java.util.Set;

import jakarta.ws.rs.core.Application;

public abstract class AppBase extends Application {

    public abstract Set<Class<?>> getClasses();
}
