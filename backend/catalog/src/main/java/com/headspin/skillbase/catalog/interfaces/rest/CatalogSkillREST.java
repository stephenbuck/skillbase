package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillService;

@Path("/skills")
public class CatalogSkillREST {

    @Inject
    private CatalogSkillService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public Response insert(CatalogSkill skill) throws URISyntaxException {
        UUID id = service.insert(skill);
        URI uri = new URI("/skills/" + id);
        return Response.ok(uri).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Response update(CatalogSkill skill) {
        return Response.ok(service.update(skill)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public Response deleteById(@PathParam("id") UUID id) {
        service.deleteById(id);
        return Response.ok().build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Response findById(@PathParam("id") UUID id) {
        Optional<CatalogSkill> match = service.findById(id);
        if (match.isPresent()) {
            return Response.ok(match).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/category/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByCategoryId")
    public List<CatalogSkill> findAllByCategoryId(@PathParam("id") UUID id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return service.findAllByCategoryId(id, sort, offset, limit);
    }
}
