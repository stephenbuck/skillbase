package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillsService;

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

/**
 * Catalog skills REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("skills")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogSkillsREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogSkillsService service;

    public CatalogSkillsREST() {
    }

    @PUT
    @Operation(summary = "Insert new catalog skill")
    public Response insert(final CatalogSkill skill) {
        final UUID skill_id = service.insert(skill);
        return Response.ok(URI.create("/skills/" + skill_id)).build();
    }

    @DELETE
    @Path("{skill_id}")
    @Operation(summary = "Delete catalog skill by id")
    public Response delete(@PathParam("skill_id") final UUID skill_id) {
        service.delete(skill_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing catalog skill")
    public Response update(final CatalogSkill skill) {
        return Response.ok(service.update(skill)).build();
    }

    @GET
    @Operation(summary = "Find all catalog skills")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{skill_id}")
    @Operation(summary = "Find catalog skill by id")
    public Response findById(@PathParam("skill_id") final UUID skill_id) {
        final Optional<CatalogSkill> match = service.findById(skill_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{skill_id}/credentials")
    @Operation(summary = "Find catalog skill credentials")
    public Response findSkillCredentials(@PathParam("skill_id") final UUID skill_id,
            @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findSkillCredentials(skill_id, sort, offset, limit)).build();
    }

    @POST
    @Path("{skill_id}/credentials/{credential_id}")
    @Operation(summary = "Insert catalog skill credential")
    public Response insertSkillCredential(@PathParam("skill_id") final UUID skill_id,
            @PathParam("credential_id") final UUID credential_id) {
        service.insertSkillCredential(skill_id, credential_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{skill_id}/credentials/{credential_id}")
    @Operation(summary = "Delete catalog skill credential")
    public Response deleteSkillCredential(@PathParam("skill_id") final UUID skill_id,
            @PathParam("credential_id") final UUID credential_id) {
        service.deleteSkillCredential(skill_id, credential_id);
        return Response.ok().build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("keyword") final String keyword,
        @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
        @QueryParam("limit") final Integer limit) {
        final List<String> list = service.search(keyword, sort, offset, limit);
        return Response.ok(String.valueOf(list)).build();
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
