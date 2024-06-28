package com.headspin.skillbase.catalog.interfaces.rest;

import java.util.List;
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

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillService;

@Path("/skills")
public class CatalogSkillREST {

    @Inject
    private CatalogSkillService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(CatalogSkill skill) {
        service.insert(skill);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public CatalogSkill update(CatalogSkill skill) {
        return service.update(skill);
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
    public CatalogSkill findById(@PathParam("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<CatalogSkill> findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByCategoryId")
    public List<CatalogSkill> findAllByCategoryId(@PathParam("id") UUID id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return service.findAllByCategoryId(id, sort, offset, limit);
    }

    @GET
    @Path("{pattern}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByTitleLike")
    public List<CatalogSkill> findAllByTitleLike(@PathParam("pattern") String pattern, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
