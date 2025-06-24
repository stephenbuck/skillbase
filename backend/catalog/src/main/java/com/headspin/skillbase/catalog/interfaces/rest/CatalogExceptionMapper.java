package com.headspin.skillbase.catalog.interfaces.rest;

import org.zalando.problem.Problem;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CatalogExceptionMapper implements ExceptionMapper<CatalogException> {

    @Override
    public Response toResponse(CatalogException exception) {
        Problem problem = Problem
                .builder()
                .withTitle("CatalogException")
                .withDetail("TBD")
                .build();
        return Response
                .status(problem.getStatus().getStatusCode())
                .entity(problem)
                .build();
    }
}
