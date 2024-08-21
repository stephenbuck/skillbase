package com.headspin.skillbase.storage.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.storage.interfaces.rest.StorageFilesREST;
import com.headspin.skillbase.common.app.AppBase;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;

/*
 * StorageApp is the main entry point.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/storage")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class StorageApp extends AppBase {

    @Inject
    private JsonWebToken jwt;

    private StorageAppConfig config;
    private StorageAppControl control;
    private StorageAppEvents events;
    private StorageAppFeatures features;
    private StorageAppHealth health;
    private StorageAppMetrics metrics;
    private StorageAppTelemetry telemetry;

    public StorageApp() {
        this.config = new StorageAppConfig();
        this.control = new StorageAppControl();
        this.events = new StorageAppEvents();
        this.features = new StorageAppFeatures();
        this.health = new StorageAppHealth();
        this.metrics = new StorageAppMetrics();
        this.telemetry = new StorageAppTelemetry();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(StorageApp.class);
        classSet.add(StorageFilesREST.class);
        return classSet;
    }
}
