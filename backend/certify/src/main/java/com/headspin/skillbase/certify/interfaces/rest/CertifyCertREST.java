package com.headspin.skillbase.certify.interfaces.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.certify.domain.CertifyCert;
import com.headspin.skillbase.certify.interfaces.service.CertifyCertService;

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

@Path("certs")
public class CertifyCertREST {

    @Inject
    private CertifyCertService service;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(
            CertifyCert cert) {
        service.insert(cert);
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public CertifyCert update(
            CertifyCert cert) {
        return service.update(cert);
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "delete")
    public void delete(
            CertifyCert cert) {
        service.delete(cert);
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
    public CertifyCert findById(
            @PathParam("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<CertifyCert> findAll(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllBySkillId")
    public List<CertifyCert> findAllBySkillId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAllBySkillId(id, sort, offset, limit);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByUserId")
    public List<CertifyCert> findAllByUserId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return service.findAllByUserId(id, sort, offset, limit);
    }
}
