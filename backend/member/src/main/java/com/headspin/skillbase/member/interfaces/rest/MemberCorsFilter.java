package com.headspin.skillbase.member.interfaces.rest;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MemberCorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, 
        final ContainerResponseContext responseContext) throws IOException {
            responseContext.getHeaders().add(
              "Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add(
              "Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add(
             "Access-Control-Allow-Headers",
             "origin, content-type, accept, authorization");
            responseContext.getHeaders().add(
              "Access-Control-Allow-Methods", 
              "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}