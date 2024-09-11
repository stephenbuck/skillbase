package com.headspin.skillbase.workflow.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;

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
 * Workflow deployments REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("deployments")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowDeploymentsREST {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private WorkflowDeploymentsService service;

    public WorkflowDeploymentsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert new workflow deployment")
    public Response insert(final WorkflowDeployment deployment) throws Exception {
        final UUID deployment_id = service.insert(deployment);
        return Response.ok(URI.create("/deployments/" + deployment_id)).build();
    }

    @DELETE
    @Path("{deployment_id}")
    @Operation(summary = "Delete workflow deployment by id")
    public Response delete(@PathParam("deployment_id") final UUID deployment_id) throws Exception {
        service.delete(deployment_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing workflow deployment")
    public Response update(final WorkflowDeployment deployment) throws Exception {
        return Response.ok(service.update(deployment)).build();
    }

    @GET
    @Operation(summary = "Find all workflow deployments")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{deployment_id}")
    @Operation(summary = "Find workflow deployment by id")
    public Response findById(@PathParam("deployment_id") final UUID deployment_id) {
        final Optional<WorkflowDeployment> match = service.findById(deployment_id);
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
