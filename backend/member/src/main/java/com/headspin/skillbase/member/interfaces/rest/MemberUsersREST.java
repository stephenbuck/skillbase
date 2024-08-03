package com.headspin.skillbase.member.interfaces.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.interfaces.service.MemberUsersService;

@Path("users")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberUsersREST {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private MemberUsersService service;

    public MemberUsersREST() {
    }

    @PUT
    @Operation(summary = "Insert member user")
    public Response insert(MemberUser user) throws URISyntaxException {
        UUID id = service.insert(user);
        URI uri = new URI("/users/" + id);
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete member user")
    public Response deleteById(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update member user")
    public Response update(MemberUser user) {
        return Response.ok(service.update(user)).build();
    }

    @GET
    @Operation(summary = "Find all member users")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find member user by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<MemberUser> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}/achievements")
    @Operation(summary = "Find member user achievements")
    public Response findUserAchievements(@PathParam("id") UUID id, @QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findUserAchievements(id, sort, offset, limit)).build();
    }

    @GET
    @Path("{id}/groups")
    @Operation(summary = "Find member user groups")
    public Response findUserGroups(@PathParam("id") UUID id, @QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findUserGroups(id, sort, offset, limit)).build();
    }

    @POST
    @Path("{id}/achievements/{achievement_id}")
    @Operation(summary = "Insert member user achievement")
    public Response insertUserAchievement(@PathParam("id") UUID id, @PathParam("achievement_id") UUID achievement_id) {
        return Response.ok(service.insertUserAchievement(id, achievement_id)).build();
    }

    @DELETE
    @Path("{id}/achievements/{achievement_id}")
    @Operation(summary = "Delete member user achievement")
    public Response deleteUserAchievement(@PathParam("id") UUID id, @PathParam("achievement_id") UUID achievement_id) {
        service.deleteUserAchievement(id, achievement_id);
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
