package com.headspin.skillbase.certificate.interfaces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.certificate.domain.Certificate;
import com.headspin.skillbase.certificate.domain.CertificateId;

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

@Path("certificate")
public class CertificateResource {

    @Inject
    private CertificateService service;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(@Valid Certificate certificate) {
        service.insert(certificate);
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Certificate update(@Valid Certificate certificate) {
        return service.update(certificate);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(@PathParam("id") CertificateId id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Certificate findById(@PathParam("id") CertificateId id) {
        return service.findById(id).orElse(null);
    }
}
