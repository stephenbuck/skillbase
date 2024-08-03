package com.headspin.skillbase.catalog.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.catalog.interfaces.rest.CatalogCategoriesREST;
import com.headspin.skillbase.catalog.interfaces.rest.CatalogCredentialsREST;
import com.headspin.skillbase.catalog.interfaces.rest.CatalogSkillsREST;
import com.headspin.skillbase.common.app.AppBase;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;

/*
 * CatalogApp is the main entry point.
 */

@ApplicationPath("/catalog")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogApp extends AppBase {

    @Inject
    private JsonWebToken jwt;

    private CatalogAppEvents evnt = new CatalogAppEvents();

    public CatalogApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(CatalogApp.class);
        classSet.add(CatalogCategoriesREST.class);
        classSet.add(CatalogCredentialsREST.class);
        classSet.add(CatalogSkillsREST.class);
        return classSet;
    }
}
