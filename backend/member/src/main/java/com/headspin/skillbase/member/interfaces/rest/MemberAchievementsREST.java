package com.headspin.skillbase.member.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.interfaces.service.MemberAchievementsService;

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
    @Operation(summary = "Insert a member achievement.")
    public Response insert(final MemberAchievement achievement) throws Exception {
        final UUID achievement_id = service.insert(achievement);
        return Response.ok(URI.create("/achievements/" + achievement_id)).build();
    }

    @DELETE
    @Path("{achievement_id}")
    @Operation(summary = "Delete a member achievement.")
    public Response deleteById(@PathParam("achievement_id") final UUID achievement_id) throws Exception {
        service.delete(achievement_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update a member achievement.")
    public Response update(final MemberAchievement achievement) throws Exception {
        return Response.ok(service.update(achievement)).build();
    }

    @GET
    @Operation(summary = "Find all member achievements.")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{achievement_id}")
    @Operation(summary = "Find a member achievement by id.")
    public Response findById(@PathParam("achievement_id") final UUID achievement_id) throws Exception {
        final Optional<MemberAchievement> match = service.findById(achievement_id);
        if (match.isPresent()) {
            return Response.ok(match.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("count")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Return a count of member achievements.")
    public Response count() {
        return Response.ok(String.valueOf(service.count()), MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Test the service.")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
