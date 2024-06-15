package com.headspin.skillbase.skill.interfaces;

import org.eclipse.microprofile.openapi.annotations.Operation;

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

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillId;

@Path("skill")
public class SkillResource {

    @Inject
    private SkillService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(@Valid Skill skill) {
        service.insert(skill);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Skill update(@Valid Skill skill) {
        return service.update(skill);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(@PathParam("id") SkillId id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Skill findById(@PathParam("id") SkillId id) {
        return service.findById(id).orElse(null);
    }
}
