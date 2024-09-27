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
 * Image files GraphQL endpoint.
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
    private ImageFilesService service;

    public ImageFilesGQL() {
    }

    @Mutation("delete")
    @Description("Delete an image.")
    public void deleteImage(@Name("image_id") final String image_id) throws Exception {
        service.deleteImage(image_id);
    }
}
