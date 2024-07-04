package com.headspin.skillbase.catalog.interfaces.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationPath("/api/v1/skillbase/catalog")
public class CatalogREST extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> cset = new HashSet<>();
        cset.add(CatalogREST.class);
        cset.add(CatalogCategoryREST.class);
        return cset;
    }
}