package com.headspin.skillbase.workflow.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.workflow.domain.Workflow;

@Path("workflow")
public class WorkflowResource {

    @Inject
    private WorkflowService service;

    @GET
    @Path("{id}/")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Workflow findById(@PathParam("id") int id) {
        return service.findById(id).orElse(null);
    }
}
