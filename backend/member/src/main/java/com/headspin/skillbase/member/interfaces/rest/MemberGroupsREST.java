package com.headspin.skillbase.member.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupsService;

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
 * Member groups REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("groups")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberGroupsREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberGroupsService service;

    public MemberGroupsREST() {
    }

    @PUT
    @Operation(summary = "Insert a member group.")
    public Response insert(final MemberGroup group) throws Exception {
        final UUID group_id = service.insert(group);
        return Response.ok(URI.create("/groups/" + group_id)).build();
    }

    @DELETE
    @Path("{group_id}")
    @Operation(summary = "Delete a member group.")
    public Response delete(@PathParam("group_id") final UUID group_id) throws Exception {
        service.delete(group_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update a member group.")
    public Response update(final MemberGroup group) throws Exception {
        return Response.ok(service.update(group)).build();
    }

    @GET
    @Operation(summary = "Find all member groups.")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{group_id}")
    @Operation(summary = "Find a member group by id.")
    public Response findById(@PathParam("group_id") final UUID group_id) throws Exception {
        final Optional<MemberGroup> match = service.findById(group_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{group_id}/users")
    @Operation(summary = "Find all member group users.")
    public Response findGroupUsers(@PathParam("group_id") final UUID group_id, @QueryParam("sort") final String sort,
            @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findGroupUsers(group_id, sort, offset, limit)).build();
    }

    @POST
    @Path("{group_id}/users/{user_id}")
    @Operation(summary = "Insert a member group user.")
    public Response insertGroupUser(@PathParam("group_id") final UUID group_id,
            @PathParam("user_id") final UUID user_id) {
        service.insertGroupUser(group_id, user_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{group_id}/users/{user_id}")
    @Operation(summary = "Delete a member group user.")
    public Response deleteGroupUser(@PathParam("group_id") final UUID group_id,
            @PathParam("user_id") final UUID user_id) {
        service.deleteGroupUser(group_id, user_id);
        return Response.ok().build();
    }

    @GET
    @Path("count")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Return a count of member groups.")
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
