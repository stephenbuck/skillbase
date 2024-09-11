package com.headspin.skillbase.image.interfaces.rest;

import org.eclipse.microprofile.auth.LoginConfig;
// import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
// import org.zalando.problem.Problem;
// import org.zalando.problem.Status;

import com.headspin.skillbase.common.providers.CommonStorageProvider;
import com.headspin.skillbase.image.interfaces.service.ImageFilesService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Image files REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Path("files")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class ImageFilesREST {

//    @Inject
//    private JsonWebToken jwt;

    @Inject
    private ImageFilesService service;

    public ImageFilesREST() {
    }

    @POST
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload an image")
    public Response uploadImage(@FormParam("file") EntityPart part) throws Exception { 
        final String image_id = service.uploadImage(
            part.getContent(),
            part.getMediaType()); 
        return Response
            .ok(image_id)
            .build();
    }

    @GET
    @Path("/{image_id}")
    @Operation(summary = "Download an image")
    public Response downloadImage(@PathParam("image_id") String image_id) throws Exception {
        final CommonStorageProvider.CommonStorageObject object =
            service.downloadImage(image_id);
        return Response
            .ok(object.input)
            .header(HttpHeaders.CONTENT_TYPE, object.type)
            .header(HttpHeaders.CONTENT_LENGTH, object.size)
            .build();
    }

    @DELETE
    @Path("/{image_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an image")
    public Response deleteImage(@PathParam("image_id") String image_id) throws Exception {
        service.deleteImage(image_id);
        return Response
            .ok()
            .build();
    }
    
    @GET
    @Path("/test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Test the service")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
