package com.headspin.skillbase.member.interfaces.rest;

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

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.interfaces.service.MemberAchievementsService;

/**
 * Member achievements REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("achievements")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberAchievementsREST {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private MemberAchievementsService service;

    public MemberAchievementsREST() {
    }

    @PUT
    @Operation(summary = "Insert member achievement")
    public Response insert(MemberAchievement achievement) {
        UUID id = service.insert(achievement);
        return Response.ok(URI.create("/achievements/" + id)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete member achievement")
    public Response deleteById(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update member achievment")
    public Response update(MemberAchievement achievement) {
        return Response.ok(service.update(achievement)).build();
    }

    @GET
    @Operation(summary = "Find all member achievments")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find member achievment by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<MemberAchievement> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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
