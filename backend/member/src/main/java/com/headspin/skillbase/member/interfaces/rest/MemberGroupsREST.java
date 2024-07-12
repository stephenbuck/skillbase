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
    @Operation(summary = "insert")
    public Response insert(MemberGroup group) throws URISyntaxException {
        UUID id = service.insert(group);
        URI uri = new URI("/groups/" + id);
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "delete")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "update")
    public Response update(MemberGroup group) {
        return Response.ok(service.update(group)).build();
    }

    @GET
    @Operation(summary = "findAll")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "findById")
    public Response findById(@PathParam("id") UUID id) {
        Optional<MemberGroup> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
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
