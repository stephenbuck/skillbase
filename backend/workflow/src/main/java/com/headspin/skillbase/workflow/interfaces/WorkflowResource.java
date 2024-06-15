package com.headspin.skillbase.workflow.interfaces;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowId;

@Path("workflow")
public class WorkflowResource {

    @Inject
    private WorkflowService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(@Valid Workflow workflow) {
        service.insert(workflow);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Workflow update(@Valid Workflow workflow) {
        return service.update(workflow);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(@PathParam("id") WorkflowId id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Workflow findById(@PathParam("id") WorkflowId id) {
        return service.findById(id).orElse(null);
    }
}
