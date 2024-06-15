package com.headspin.skillbase.member.interfaces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.member.domain.Member;
import com.headspin.skillbase.member.domain.MemberId;

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

@Path("member")
public class MemberResource {

    @Inject
    private MemberService service;

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(@Valid Member member) {
        service.insert(member);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Member update(@Valid Member member) {
        return service.update(member);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "deleteById")
    public void deleteById(@PathParam("id") MemberId id) {
        service.deleteById(id);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findById")
    public Member findById(@PathParam("id") MemberId id) {
        return service.findById(id).orElse(null);
    }
}
