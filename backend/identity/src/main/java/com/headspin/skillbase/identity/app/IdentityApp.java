package com.headspin.skillbase.identity.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.identity.interfaces.rest.IdentityGroupsREST;
import com.headspin.skillbase.identity.interfaces.rest.IdentityRolesREST;
import com.headspin.skillbase.identity.interfaces.rest.IdentityUsersREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/*
 * IdentityApp is the main entry point.
 */

@ApplicationPath("/identity")
public class IdentityApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(IdentityGroupsREST.class);
        classSet.add(IdentityRolesREST.class);
        classSet.add(IdentityUsersREST.class);
        return classSet;
    }
}
