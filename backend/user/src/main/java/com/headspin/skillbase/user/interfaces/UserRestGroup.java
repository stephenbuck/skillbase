package com.headspin.skillbase.user.interfaces;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.user.domain.Group;

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

@Path("group")
public class UserRestGroup {

//    @Inject
//    private UserService service;

    @PUT
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Operation(summary = "insert")
    public void insert(
        Group group) {
//        service.insert(group);
    }

    @POST
    @Path("")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "update")
    public Group update(
        Group group) {
//        return service.update(group);
        return null;
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
    public Group findById(
        @PathParam("id") UUID id) {
//        return service.findById(id).orElse(null);
        return null;
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAll")
    public List<Group> findAll(
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAll(sort, offset, limit);
        return null;
    }
    
    @GET
    @Path("{pattern}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByGroupNameLike")
    public List<Group> findAllByGroupNameLike(
            @PathParam("pattern") String pattern,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAllByGroupNameLike(pattern, sort, offset, limit);
        return null;
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(summary = "findAllByGroupd")
    public List<Group> findAllByGroupId(
            @PathParam("id") UUID id,
            @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
//        return service.findAllByGroupId(id, sort, offset, limit);
        return null;
    }
}
