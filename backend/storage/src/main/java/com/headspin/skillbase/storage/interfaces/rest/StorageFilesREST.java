package com.headspin.skillbase.storage.interfaces.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
// import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
// import org.zalando.problem.Problem;
// import org.zalando.problem.Status;

import com.headspin.skillbase.storage.interfaces.service.StorageFilesService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Storage files REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("files")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class StorageFilesREST {

//    @Inject
//    private JsonWebToken jwt;

    @Inject
    private StorageFilesService service;

    public StorageFilesREST() {
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "upload")
    public Response upload(List<EntityPart> parts) {
        try {
            UUID home = UUID.randomUUID();
            parts.forEach(part -> {
                try {
                    UUID uuid = UUID.randomUUID();
                    service.upload(home, part.getContent(), uuid);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/download/{uuid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "download")
    public Response download(@PathParam("uuid") UUID uuid) {
        try {
            UUID home = UUID.randomUUID();
            return Response.ok(service.resolvePath(home, uuid)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    // Copy

    // Move

    // Mkdir

    // Rmdir

    // Rename

    // Exists

    @DELETE
    @Path("/delete/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "delete")
    public Response delete(@PathParam("uuid") UUID uuid) {
        try {
            UUID home = UUID.randomUUID();
            service.delete(home, uuid);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list")
    public Response list() {
        try {
            UUID home = UUID.randomUUID();
            UUID uuid = UUID.randomUUID();
            return Response.ok(service.list(home, uuid)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "test")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
