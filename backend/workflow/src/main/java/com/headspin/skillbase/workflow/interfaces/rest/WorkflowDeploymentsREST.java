package com.headspin.skillbase.workflow.interfaces.rest;

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

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;

/**
 * REST resource for workflow deployments.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("deployments")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class WorkflowDeploymentsREST {

    @Inject
    private WorkflowDeploymentsService service;

    public WorkflowDeploymentsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert workflow deployment")
    public Response insert(WorkflowDeployment deployment) throws URISyntaxException {
        UUID id = service.insert(deployment);
        URI uri = new URI("/deployments/" + id);
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete workflow deployment")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update workflow deployment")
    public Response update(WorkflowDeployment deployment) {
        return Response.ok(service.update(deployment)).build();
    }

    @GET
    @Operation(summary = "Find all workflow deployments")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Find workflow deployment by ID")
    public Response findById(@PathParam("id") UUID id) {
        Optional<WorkflowDeployment> match = service.findById(id);
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
