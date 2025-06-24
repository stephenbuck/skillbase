package com.headspin.skillbase.catalog.interfaces.rest;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class CatalogException extends WebApplicationException {
    public CatalogException(String message) {
        super(Response.status(Status.BAD_REQUEST).entity(message).type("text/plain").build());
    }
}
