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

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoryService;

@Path("categories")
public class CatalogCategoryREST {

    @Inject
    private CatalogCategoryService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(
        CatalogCategory category) {
        service.insert(category);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public CatalogCategory update(
        CatalogCategory category) {
        return service.update(category);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(
        @PathParam("id") UUID id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public CatalogCategory findById(
        @PathParam("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<CatalogCategory> findAll(
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
    
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByParentId")
    public List<CatalogCategory> findAllByParentId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAllByParentId(id, sort, offset, limit);
    }
    
    @GET
    @Path("{pattern}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByTitleLike")
    public List<CatalogCategory> findAllByTitleLike(
            @PathParam("pattern") String pattern,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
