package com.headspin.skillbase.identity.interfaces.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.interfaces.service.IdentityGroupService;

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

@Path("/groups")
public class IdentityGroupREST {

    @Inject
    private IdentityGroupService service;

    @PUT
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public UUID insert(IdentityGroup group) {
        return service.insert(group);
    }

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public IdentityGroup update(IdentityGroup group) {
        return service.update(group);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(@PathParam("id") UUID id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public IdentityGroup findById(@PathParam("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<IdentityGroup> findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
