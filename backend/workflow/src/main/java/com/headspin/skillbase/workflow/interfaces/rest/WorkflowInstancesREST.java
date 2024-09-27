package com.headspin.skillbase.workflow.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

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
    @Operation(summary = "Insert a workflow instance.")
    public Response insert(final WorkflowInstance instance) throws Exception {
        final UUID instance_id = service.insert(instance);
        return Response.ok(URI.create("/instances/" + instance_id)).build();
    }

    @DELETE
    @Path("{instance_id}")
    @Operation(summary = "Delete a workflow instance.")
    public Response deleteById(@PathParam("instance_id") final UUID instance_id) throws Exception {
        service.delete(instance_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update a workflow instance.")
    public Response update(final WorkflowInstance instance) throws Exception {
        return Response.ok(service.update(instance)).build();
    }

    @GET
    @Operation(summary = "Find all workflow instances.")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{instance_id}")
    @Operation(summary = "Find a workflow instance by id.")
    public Response findById(@PathParam("instance_id") final UUID instance_id) {
        final Optional<WorkflowInstance> match = service.findById(instance_id);
        if (match.isPresent()) {
            return Response.ok(match.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("count")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Return a count of workflow instances.")
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
