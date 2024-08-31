package com.headspin.skillbase.image.interfaces.rest;

import java.io.InputStream;

import org.eclipse.microprofile.auth.LoginConfig;
// import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
// import org.zalando.problem.Problem;
// import org.zalando.problem.Status;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Image files REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

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
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload an image")
    public Response upload(@FormParam("file") InputStream input) {
        try {
            String image_id = service.uploadObject(input, Long.valueOf(-1));
            return Response.ok(image_id).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/download/{image_id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Download an image")
    public Response download(@PathParam("image_id") String image_id) {
        try {
            return Response.ok(service.downloadObject(image_id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{image_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an image")
    public Response delete(@PathParam("image_id") String image_id) {
        try {
            service.deleteObject(image_id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("/test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Test the service")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
