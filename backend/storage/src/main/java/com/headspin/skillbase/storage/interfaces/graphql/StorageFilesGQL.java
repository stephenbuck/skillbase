package com.headspin.skillbase.storage.interfaces.graphql;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.storage.interfaces.service.StorageFilesService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Storage files GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class StorageFilesGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private StorageFilesService service;

    public StorageFilesGQL() {
    }
}
