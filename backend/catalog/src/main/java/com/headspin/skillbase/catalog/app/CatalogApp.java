package com.headspin.skillbase.catalog.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.catalog.interfaces.rest.CatalogCategoriesREST;
import com.headspin.skillbase.catalog.interfaces.rest.CatalogCredentialsREST;
import com.headspin.skillbase.catalog.interfaces.rest.CatalogSkillsREST;
import com.headspin.skillbase.common.app.AppBase;

import jakarta.ws.rs.ApplicationPath;

/*
 * CatalogApp is the main entry point.
 */

@ApplicationPath("/catalog")
public class CatalogApp extends AppBase {

    public CatalogApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(CatalogApp.class);
//        classSet.add(CatalogAppHealth.class);
        classSet.add(CatalogCategoriesREST.class);
        classSet.add(CatalogCredentialsREST.class);
        classSet.add(CatalogSkillsREST.class);
        return classSet;
    }
}
