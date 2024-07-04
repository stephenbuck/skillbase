package com.headspin.skillbase.identity.interfaces.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.interfaces.service.IdentityUserService;

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

/*
 * IdentityUsersREST implements a REST resource for the User
 * domain.
 */

@Path("/users")
public class IdentityUsersREST {

    @Inject
    private IdentityUserService service;

    @PUT
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public UUID insert(IdentityUser user) {
        return service.insert(user);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "delete")
    public void delete(@PathParam("id") UUID id) {
        service.delete(id);
    }

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public IdentityUser update(IdentityUser user) {
        return service.update(user);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public IdentityUser findById(@PathParam("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<IdentityUser> findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @GET
    @Path("/count")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "count")
    public Long count() {
        return service.count();
    }
}
