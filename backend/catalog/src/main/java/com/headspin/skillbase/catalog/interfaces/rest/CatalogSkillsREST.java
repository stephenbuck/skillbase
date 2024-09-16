package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillsService;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Catalog skills REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("skills")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CatalogSkillsREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogSkillsService service;

    public CatalogSkillsREST() {
    }

    @PUT
    @Operation(summary = "Insert new catalog skill")
    public Response insert(final CatalogSkill skill) throws Exception {
        final UUID skill_id = service.insert(skill);
        return Response.ok(URI.create("/skills/" + skill_id)).build();
    }

    @DELETE
    @Path("{skill_id}")
    @Operation(summary = "Delete catalog skill by id")
    public Response delete(@PathParam("skill_id") final UUID skill_id) throws Exception {
        service.delete(skill_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing catalog skill")
    public Response update(final CatalogSkill skill) throws Exception {
        return Response.ok(service.update(skill)).build();
    }

    @GET
    @Operation(summary = "Find all catalog skills")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    @GET
    @Path("{skill_id}")
    @Operation(summary = "Find catalog skill by id")
    public Response findById(@PathParam("skill_id") final UUID skill_id) throws Exception {
        final Optional<CatalogSkill> match = service.findById(skill_id);
        if (match.isPresent()) {
            return Response.ok(match.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{skill_id}/credentials")
    @Operation(summary = "Find catalog skill credentials")
    public Response findSkillCredentials(@PathParam("skill_id") final UUID skill_id,
            @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findSkillCredentials(skill_id, sort, offset, limit)).build();
    }

    @POST
    @Path("{skill_id}/credentials/{credential_id}")
    @Operation(summary = "Insert catalog skill credential")
    public Response insertSkillCredential(@PathParam("skill_id") final UUID skill_id,
            @PathParam("credential_id") final UUID credential_id) {
        service.insertSkillCredential(skill_id, credential_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{skill_id}/credentials/{credential_id}")
    @Operation(summary = "Delete catalog skill credential")
    public Response deleteSkillCredential(@PathParam("skill_id") final UUID skill_id,
            @PathParam("credential_id") final UUID credential_id) {
        service.deleteSkillCredential(skill_id, credential_id);
        return Response.ok().build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("keyword") final String keyword,
            @QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response.ok(service.search(keyword, sort, offset, limit)).build();
    }

    @POST
    @Path("{skill_id}/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload skill image")
    public Response uploadImage(
            @PathParam("skill_id") UUID skill_id,
            @FormParam("file") EntityPart part) throws Exception {
        final String object_id = service.uploadImage(
                skill_id,
                part.getContent(),
                -1L,
                part.getMediaType());
        return Response
                .ok(object_id)
                .build();
    }

    @GET
    @Path("/{skill_id}/image")
    @Operation(summary = "Download skill image")
    public Response downloadImage(
            @PathParam("skill_id") UUID skill_id) throws Exception {
        final CommonStorageProvider.CommonStorageObject object = service.downloadImage(skill_id);
        return Response
                .ok(object.input)
                .header(HttpHeaders.CONTENT_TYPE, object.type)
                .header(HttpHeaders.CONTENT_LENGTH, object.size)
                .build();
    }

    @DELETE
    @Path("/{skill_id}/image")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete skill image")
    public Response deleteImage(@PathParam("skill_id") UUID skill_id) throws Exception {
        service.deleteImage(skill_id);
        return Response.ok().build();
    }

    @GET
    @Path("count")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "count")
    public Response count() {
        return Response.ok(String.valueOf(service.count()), MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "test")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }
}
