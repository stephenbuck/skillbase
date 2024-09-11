package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCredentialsService;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Catalog categories REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("credentials")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogCredentialsREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCredentialsService service;

    public CatalogCredentialsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert new catalog skill credential")
    public Response insert(final CatalogCredential credential) throws Exception {
        final UUID credential_id = service.insert(credential);
        return Response.ok(URI.create("/credentials/" + credential_id)).build();
    }

    @DELETE
    @Path("{credential_id}")
    @Operation(summary = "Delete catalog skill credential by id")
    public Response delete(@PathParam("credential_id") final UUID credential_id) throws Exception {
        service.delete(credential_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing catalog skill credential")
    public Response update(final CatalogCredential credential) throws Exception {
        return Response.ok(service.update(credential)).build();
    }

    @GET
    @Operation(summary = "Find all catalog skill credentials")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{credential_id}")
    @Operation(summary = "Find catalog skill credential by id")
    public Response findById(@PathParam("credential_id") final UUID credential_id) {
        final Optional<CatalogCredential> match = service.findById(credential_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{credential_id}/start")
    @Operation(summary = "start")
    public Response start(@PathParam("credential_id") final UUID credential_id) {
        return Response.ok(service.start(credential_id)).build();
    }

    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload credential image")
    public Response uploadImage(@FormParam("file") EntityPart part) throws Exception { 
        String object_id = service.uploadImage(
            part.getContent(),
            -1L,
            part.getMediaType()); 
        return Response
            .ok(object_id)
            .build();
    }

    @GET
    @Path("/image/{image_id}")
    @Operation(summary = "Download credential image")
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
    @Path("/image/{image_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete credential image")
    public Response deleteImage(@PathParam("image_id") String image_id) throws Exception {
        service.deleteImage(image_id);
        return Response.ok().build();
    }

    @POST
    @Path("/bpmn")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload credential BPMN")
    public Response uploadBPMN(@FormParam("file") EntityPart part) throws Exception { 
        String object_id = service.uploadBPMN(
            part.getContent(),
            -1L,
            part.getMediaType()); 
        return Response
            .ok(object_id)
            .build();
    }

    @GET
    @Path("/bpmn/{bpmn_id}")
    @Operation(summary = "Download credential BPMN")
    public Response downloadBPMN(@PathParam("bpmn_id") String bpmn_id) throws Exception {
        final CommonStorageProvider.CommonStorageObject object =
            service.downloadBPMN(bpmn_id);
        // MessageDigest digest = MessageDigest.getInstance("SHA-256");
        EntityTag tag = new EntityTag(bpmn_id);
        Response r = Response
            .ok(object.input)
            .tag(tag)
            .header(HttpHeaders.CONTENT_TYPE, object.type)
            .header(HttpHeaders.CONTENT_LENGTH, object.size)
            .build();
        return r;
    }

    @DELETE
    @Path("/bpmn/{bpmn_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete credential BPMN")
    public Response deleteBPMN(@PathParam("bpmn_id") String bpmn_id) throws Exception {
        service.deleteBPMN(bpmn_id);
        return Response.ok().build();
    }

    @GET
    @Path("count")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "count")
    public Response count() {
        return Response.ok(String.valueOf(service.count()), MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "test")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
