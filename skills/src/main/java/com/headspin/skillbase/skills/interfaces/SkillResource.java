package com.headspin.skillbase.skills.interfaces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.headspin.skillbase.skills.domain.Skill;

@Path("skills")
public class SkillResource {

    @Inject
    private SkillService service;

    @GET
    @Path("{id}/")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Skill findById(@PathParam("id") int id) {
        return service.findById(id).orElse(null);
    }
}
