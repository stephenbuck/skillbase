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

    @Inject
    private ImageAppHome home;

    public ImageApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classSet = new HashSet<>();
        classSet.add(ImageApp.class);
        classSet.add(ImageFilesREST.class);
        return classSet;
    }
}
