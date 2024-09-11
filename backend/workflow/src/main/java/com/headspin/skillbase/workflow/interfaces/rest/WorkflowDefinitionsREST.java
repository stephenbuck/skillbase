package com.headspin.skillbase.workflow.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;

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
 * Workflow definitions REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("definitions")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowDefinitionsREST {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private WorkflowDefinitionsService service;

    public WorkflowDefinitionsREST() {
    }
    
    @PUT
    @Operation(summary = "Insert new workflow definition")
    public Response insert(final WorkflowDefinition definition) throws Exception {
        final UUID definition_id = service.insert(definition);
        return Response.ok(URI.create("/definitions/" + definition_id)).build();
    }

    @DELETE
    @Path("{definition_id}")
    @Operation(summary = "Delete workflow definition by id")
    public Response delete(@PathParam("definition_id") final UUID definition_id) throws Exception {
        service.delete(definition_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing workflow definition")
    public Response update(final WorkflowDefinition definition) throws Exception {
        return Response.ok(service.update(definition)).build();
    }

    @GET
    @Operation(summary = "Find all workflow definitions")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) throws Exception {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{definition_id}")
    @Operation(summary = "Find workflow definition by id")
    public Response findById(@PathParam("definition_id") final UUID definition_id) {
        final Optional<WorkflowDefinition> match = service.findById(definition_id);
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
