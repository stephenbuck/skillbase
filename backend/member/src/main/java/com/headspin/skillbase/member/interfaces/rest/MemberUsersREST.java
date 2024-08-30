package com.headspin.skillbase.member.interfaces.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.interfaces.service.MemberUsersService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
 * Member users REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

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
    @Operation(summary = "Insert new member user")
    public Response insert(final MemberUser user) {
        final UUID user_id = service.insert(user);
        return Response.ok(URI.create("/users/" + user_id)).build();
    }

    @DELETE
    @Path("{user_id}")
    @Operation(summary = "Delete member user by id")
    public Response deleteById(@PathParam("user_id") final UUID user_id) {
        service.delete(user_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing member user")
    public Response update(final MemberUser user) {
        return Response.ok(service.update(user)).build();
    }

    @POST
    @Path("{user_id}/image")
    @Operation(summary = "Upload member image")
    public Response uploadImage(@PathParam("user_id") final UUID user_id, final InputStream input, final Long size) throws Exception {
        String image_id = service.uploadImage(user_id, input, size);
        return Response.ok(image_id).build();
    }

    @GET
    @Path("{user_id}/image")
    @Operation(summary = "Download member image")
    public Response downloadImage(@PathParam("user_id") final UUID user_id) throws Exception {
        final InputStream input = service.downloadImage(user_id);
        return Response.ok(input).build();
    }

    @DELETE
    @Path("{user_id}/image")
    @Operation(summary = "Delete member image")
    public Response deleteImage(@PathParam("user_id") final UUID user_id) throws Exception {
        service.deleteImage(user_id);
        return Response.ok().build();
    }

    @GET
    @Operation(summary = "Find all member users")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{user_id}")
    @Operation(summary = "Find member user by id")
    public Response findById(@PathParam("user_id") final UUID user_id) {
        final Optional<MemberUser> match = service.findById(user_id);
        if (match.isPresent()) {
            return Response.ok(match.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{user_id}/achievements")
    @Operation(summary = "Find member user achievements")
    public Response findUserAchievements(@PathParam("user_id") final UUID user_id, @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findUserAchievements(user_id, sort, offset, limit)).build();
    }

    @GET
    @Path("{user_id}/groups")
    @Operation(summary = "Find member user groups")
    public Response findUserGroups(@PathParam("user_id") final UUID user_id, @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findUserGroups(user_id, sort, offset, limit)).build();
    }

    @POST
    @Path("{user_id}/achievements/{achievement_id}")
    @Operation(summary = "Insert member user achievement")
    public Response insertUserAchievement(@PathParam("user_id") final UUID user_id, @PathParam("achievement_id") final UUID achievement_id) {
        service.insertUserAchievement(user_id, achievement_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{user_ids}/achievements/{achievement_id}")
    @Operation(summary = "Delete member user achievement")
    public Response deleteUserAchievement(@PathParam("user_id") final UUID user_id, @PathParam("achievement_id") final UUID achievement_id) {
        service.deleteUserAchievement(user_id, achievement_id);
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
