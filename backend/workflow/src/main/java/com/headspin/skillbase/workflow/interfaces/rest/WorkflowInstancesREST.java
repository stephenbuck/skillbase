package com.headspin.skillbase.workflow.interfaces.rest;

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

import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

/**
 * Workflow instances REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("instances")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowInstancesREST {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private WorkflowInstancesService service;

    public WorkflowInstancesREST() {
    }

    @PUT
    @Operation(summary = "Insert workflow instance")
    public Response insert(WorkflowInstance instance) {
        UUID id = service.insert(instance);
        return Response.ok(URI.create("/instances/" + id)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete workflow instance")
    public Response deleteById(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update workflow instance")
    public Response update(WorkflowInstance instance) {
        return Response.ok(service.update(instance)).build();
    }

    @GET
    @Operation(summary = "Find all workflow instances")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find workflow instance by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<WorkflowInstance> match = service.findById(id);
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
