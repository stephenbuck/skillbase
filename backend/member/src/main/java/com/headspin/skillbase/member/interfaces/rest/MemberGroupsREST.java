package com.headspin.skillbase.member.interfaces.rest;

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

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupService;

@Path("groups")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class MemberGroupsREST {

    @Inject
    private MemberGroupService service;

    public MemberGroupsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert member group")
    public Response insert(MemberGroup group) throws URISyntaxException {
        UUID id = service.insert(group);
        URI uri = new URI("/groups/" + id);
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete member group")
    public Response delete(@PathParam("id") UUID id) {
        return Response.ok(service.delete(id)).build();
    }

    @POST
    @Operation(summary = "Update member group")
    public Response update(MemberGroup group) {
        return Response.ok(service.update(group)).build();
    }

    @GET
    @Operation(summary = "Find all member groups")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find member group by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<MemberGroup> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}/users")
    @Operation(summary = "Find member group users")
    public Response findGroupUsers(@PathParam("id") UUID id, @QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findGroupUsers(id, sort, offset, limit)).build();
    }
    
    @POST
    @Path("{id}/users/{user_id}")
    @Operation(summary = "Insert member group user")
    public Response insertGroupUser(@PathParam("id") UUID id, @PathParam("user_id") UUID user_id) {
        return Response.ok(service.insertGroupUser(id, user_id)).build();
    }

    @DELETE
    @Path("{id}/users/{user_id}")
    @Operation(summary = "Delete member group user")
    public Response deleteGroupUser(@PathParam("id") UUID id, @PathParam("user_id") UUID user_id) {
        return Response.ok(service.deleteGroupUser(id, user_id)).build();
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
