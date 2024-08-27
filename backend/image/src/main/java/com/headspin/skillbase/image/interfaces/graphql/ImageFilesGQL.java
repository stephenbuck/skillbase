package com.headspin.skillbase.image.interfaces.graphql;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.image.interfaces.service.ImageFilesService;

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
public class ImageFilesGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private StorageFilesService service;

    public StorageFilesGQL() {
    }

    @Mutation("delete")
    @Description("Delete a file")
    public void delete(@Name("uuid") final UUID uuid) throws IOException {
        service.delete(uuid, uuid);
    }

    @Query("list")
    @Description("List files")
    public List<String> list() throws IOException {
        return service.list(null, null);
    }
}
