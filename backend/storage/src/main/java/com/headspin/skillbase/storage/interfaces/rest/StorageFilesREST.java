package com.headspin.skillbase.storage.interfaces.rest;

import java.io.IOException;
import java.io.InputStream;
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
import jakarta.ws.rs.core.PathSegment;
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

    @GET
    @Path("/foo/{segments:.*}/list")
    public Response foo(@PathParam("segments") List<PathSegment> segments) {
        // TBD
        return Response.ok().build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload a file")
    public Response upload(List<EntityPart> parts) {
        try {
            UUID home = UUID.randomUUID();
            parts.forEach(part -> {
                try {
                    UUID uuid = UUID.randomUUID();
//                    service.upload(home, part.getContent(), uuid);
                    service.uploadObject(String.valueOf(uuid), part.getContent(), Long.valueOf(0));
                }
                catch (Exception e) {
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
    @Operation(summary = "Download a file")
    public Response download(@PathParam("uuid") UUID uuid) {
        try {
            return Response.ok(service.downloadObject(String.valueOf(uuid))).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    // Copy
    @POST
    @Path("/{uuid}/copy")
    @Operation(summary = "Copy a file")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response copy(@PathParam("uuid") UUID uuid) {
        return null;
    }

    // Move
    @POST
    @Path("/{uuid}/move")
    @Operation(summary = "Move a file")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response move(@PathParam("uuid") UUID uuid) {
        return null;
    }

    // Mkdir
    @POST
    @Path("/{uuid}/mkdir")
    @Operation(summary = "Make a directory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response mkdir(@PathParam("uuid") UUID uuid) {
        return null;
    }

    // Rmdir
    @POST
    @Path("/{uuid}/rmdir")
    @Operation(summary = "Remove a directory")
    public Response rmdir(@PathParam("uuid") UUID uuid) {
        return null;
    }

    // Rename
    @POST
    @Path("{uuid}/rename")
    @Operation(summary = "Rename a file")
    public Response rename(@PathParam("uuid") UUID uuid) {
        return null;
    }

    // Exists
    @POST
    @Path("{uuid}/exists")
    @Operation(summary = "Check if a file exists")
    public Response exists(@PathParam("uuid") UUID uuid) {
        return null;
    }

    @DELETE
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a file")
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
    @Operation(summary = "List files")
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
    @Operation(summary = "Test the service")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
