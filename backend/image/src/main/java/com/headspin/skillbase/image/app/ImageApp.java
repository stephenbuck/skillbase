package com.headspin.skillbase.image.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.image.interfaces.rest.ImageFilesREST;
import com.headspin.skillbase.common.app.AppBase;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;

/*
 * ImageApp is the main entry point.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/image")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class ImageApp extends AppBase {

    @Inject
    private JsonWebToken jwt;

    private ImageAppConfig config;
    private ImageAppControl control;
    private ImageAppEvents events;
    private ImageAppFeatures features;
    private ImageAppHealth health;
    private ImageAppTelemetry telemetry;

    public ImageApp() {
        this.config = new ImageAppConfig();
        this.control = new ImageAppControl();
        this.events = new ImageAppEvents();
        this.features = new ImageAppFeatures();
        this.health = new ImageAppHealth();
        this.telemetry = new ImageAppTelemetry();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(ImageApp.class);
        classSet.add(ImageFilesREST.class);
        return classSet;
    }
}
