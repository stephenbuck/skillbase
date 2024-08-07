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

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupsService;

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
    @Operation(summary = "Insert new member group")
    public Response insert(MemberGroup group) {
        UUID group_id = service.insert(group);
        return Response.ok(URI.create("/groups/" + group_id)).build();
    }

    @DELETE
    @Path("{group_id}")
    @Operation(summary = "Delete member group by id")
    public Response delete(@PathParam("group_id") UUID group_id) {
        service.delete(group_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing member group")
    public Response update(MemberGroup group) {
        return Response.ok(service.update(group)).build();
    }

    @GET
    @Operation(summary = "Find all member groups")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{group_id}")
    @Operation(summary = "Find member group by id")
    public Response findById(@PathParam("group_id") UUID group_id) {
        Optional<MemberGroup> match = service.findById(group_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{group_id}/users")
    @Operation(summary = "Find member group users")
    public Response findGroupUsers(@PathParam("group_id") UUID group_id, @QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findGroupUsers(group_id, sort, offset, limit)).build();
    }
    
    @POST
    @Path("{group_id}/users/{user_id}")
    @Operation(summary = "Insert member group user")
    public Response insertGroupUser(@PathParam("group_id") UUID group_id, @PathParam("user_id") UUID user_id) {
        service.insertGroupUser(group_id, user_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{group_id}/users/{user_id}")
    @Operation(summary = "Delete member group user")
    public Response deleteGroupUser(@PathParam("group_id") UUID group_id, @PathParam("user_id") UUID user_id) {
        service.deleteGroupUser(group_id, user_id);
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
