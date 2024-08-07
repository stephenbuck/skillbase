package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCredentialsService;

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
    public Response insert(CatalogCredential credential) {
        UUID credential_id = service.insert(credential);
        return Response.ok(URI.create("/credentials/" + credential_id)).build();
    }

    @DELETE
    @Path("{credential_id}")
    @Operation(summary = "Delete catalog skill credential by id")
    public Response delete(@PathParam("credential_id") UUID credential_id) {
        service.delete(credential_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing catalog skill credential")
    public Response update(CatalogCredential credential) {
        return Response.ok(service.update(credential)).build();
    }

    @GET
    @Operation(summary = "Find all catalog skill credentials")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{credential_id}")
    @Operation(summary = "Find catalog skill credential by id")
    public Response findById(@PathParam("credential_id") UUID credential_id) {
        Optional<CatalogCredential> match = service.findById(credential_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{credential_id}/start")
    @Operation(summary = "start")
    public Response start(@PathParam("credential_id") UUID credential_id) {
        return Response.ok(service.start(credential_id)).build();
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
