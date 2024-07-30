package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

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

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillService;

@Path("skills")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CatalogSkillsREST {

    @Inject
    private CatalogSkillService service;

    public CatalogSkillsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert catalog skill")
    public Response insert(CatalogSkill skill) throws URISyntaxException {
        UUID id = service.insert(skill);
        URI uri = new URI("/skills/" + id);
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete catalog skill")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update catalog skill")
    public Response update(CatalogSkill skill) {
        return Response.ok(service.update(skill)).build();
    }

    @GET
    @Operation(summary = "Find all catalog skills")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find catalog skill by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<CatalogSkill> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}/credentials")
    @Operation(summary = "Find catalog skill credentials")
    public Response findSkillCredentials(@PathParam("id") UUID id, @QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findSkillCredentials(id, sort, offset, limit)).build();
    }

    @POST
    @Path("{id}/credentials/{credential_id}")
    @Operation(summary = "Insert catalog skill credential")
    public Response insertSkillCredential(@PathParam("id") UUID id, @PathParam("credential_id") UUID credential_id) {
        return Response.ok(service.insertSkillCredential(id, credential_id)).build();
    }

    @DELETE
    @Path("{id}/credentials/{credential_id}")
    @Operation(summary = "Delete catalog skill credential")
    public Response deleteSkillCredential(@PathParam("id") UUID id, @PathParam("credential_id") UUID credential_id) {
        return Response.ok(service.deleteSkillCredential(id, credential_id)).build();
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
