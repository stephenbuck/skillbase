package com.headspin.skillbase.cert.interfaces;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.cert.domain.Document;

// import jakarta.inject.Inject;
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

@Path("document")
public class CertRestDocument {

//    @Inject
//    private CertService service;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(
            Document document) {
//        service.insert(document);
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Document update(
            Document document) {
//        return service.update(document);
        return null;
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "delete")
    public void delete(
            Document document) {
//        service.delete(document);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(
            @PathParam("id") UUID id) {
//        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Document findById(
            @PathParam("id") UUID id) {
//        return service.findById(id).orElse(null);
        return null;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<Document> findAll(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAll(sort, offset, limit);
        return null;
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllBySkillId")
    public List<Document> findAllBySkillId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAllBySkillId(id, sort, offset, limit);
        return null;
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByUserId")
    public List<Document> findAllByUserId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAllByUserId(id, sort, offset, limit);
        return null;
    }
}
