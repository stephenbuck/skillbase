package com.headspin.skillbase.members.interfaces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.members.domain.Member;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("member")
public class MemberResource {

    @Inject
    private MemberService service;

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Member findById(@PathParam("id") int id) {
        return service.findById(id).orElse(null);
    }
}
