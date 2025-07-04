package com.headspin.skillbase.catalog.interfaces.rest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.slf4j.MDC;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoriesService;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TransactionRequiredException;
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
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Catalog categories REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Path("categories")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CatalogCategoriesREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCategoriesService service;

    private final CacheControl cacheControl;

    public CatalogCategoriesREST() {
        this.cacheControl = new CacheControl();
        cacheControl.setMaxAge(3600);
    }

    /*
     * @ApiResponses(value = {
     * 
     * @ApiResponse(responseCode = "400", description = "Invalid category"),
     * 
     * @ApiResponse(responseCode = "400", description = "Category already exists"),
     * 
     * @ApiResponse(responseCode = "500", description = "Internal server error")
     * })
     */
    @PUT
    @Operation(summary = "Insert a catalog category.")
    public Response insert(final CatalogCategory category) throws Exception {

        // Set up the mapped diagnostic context
        MDC.clear();
        MDC.put("X-Correlation-Id", String.valueOf(UUID.randomUUID()));

        try {
            final UUID category_id = service.insert(category);
            return Response.ok(category).build();
        } catch (final EntityExistsException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Problem.valueOf(Status.BAD_REQUEST))
                    .build();
        } catch (final IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Problem.valueOf(Status.BAD_REQUEST))
                    .build();
        } catch (final TransactionRequiredException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Problem.valueOf(Status.INTERNAL_SERVER_ERROR))
                    .build();
        } finally {
            MDC.clear();
        }
    }

    @DELETE
    @Path("{category_id}")
    @Operation(summary = "Delete a catalog category.")
    public Response deleteById(@PathParam("category_id") final UUID category_id) {
        service.delete(category_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update a catalog category.")
    public Response update(final CatalogCategory category) throws Exception {
        return Response.ok(service.update(category)).build();
    }

    /*
     * @ApiResponses(value = {
     * 
     * @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
     * 
     * @ApiResponse(responseCode = "404", description = "Category not found"),
     * 
     * @ApiResponse(responseCode = "500", description = "Internal server error")
     * })
     */
    @GET
    @Path("{category_id}")
    @Operation(summary = "Find a catalog category by id.")
    public Response findById(@Context Request request, @PathParam("category_id") final UUID category_id)
            throws Exception {

        try {

            // Find the category
            final CatalogCategory entity = service.findById(category_id).get();

            // Generate ETag for the resource
            final EntityTag etag = new EntityTag(entity.toETag());

            // If client's ETag matches the current ETag...
            Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
            if (builder != null) {
                return builder.build();
            }

            // Otherwise, fetch and return the resource with a new ETag
            else {
                return Response
                        .ok(entity, MediaType.APPLICATION_JSON)
                        .tag(etag)
                        .cacheControl(cacheControl)
                        .header(HttpHeaders.EXPIRES, expiresAt(60))
                        .build();
            }

        } catch (final IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Problem.valueOf(Status.NOT_FOUND))
                    .build();
        } catch (final NoSuchElementException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(Problem.valueOf(Status.NOT_FOUND))
                    .build();
        }
    }

    @GET
    @Operation(summary = "Find all catalog categories.")
    public Response findAll(@QueryParam("sort") final String sort, @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        return Response
                .ok(service.findAll(sort, offset, limit))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("{category_id}/categories")
    @Operation(summary = "Find all catalog category subcategories.")
    public Response findCategoryCategories(@PathParam("category_id") final UUID category_id,
            @QueryParam("sort") final String sort,
            @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response
                .ok(service.findCategoryCategories(category_id, sort, offset, limit))
                .build();
    }

    @POST
    @Path("{category_id}/categories/{subcategory_id}")
    @Operation(summary = "Insert a catalog category subcategory.")
    public Response insertCategoryCategory(@PathParam("category_id") final UUID category_id,
            @PathParam("subcategory_id") final UUID subcategory_id) {
        service.insertCategoryCategory(category_id, subcategory_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{category_id}/categories/{subcategory_id}")
    @Operation(summary = "Delete a catalog category subcategory.")
    public Response deleteCategoryCategory(@PathParam("category_id") final UUID category_id,
            @PathParam("subcategory_id") final UUID subcategory_id) {
        service.deleteCategoryCategory(category_id, category_id);
        return Response.ok().build();
    }

    @GET
    @Path("{category_id}/skills")
    @Operation(summary = "Find all catalog category skills.")
    public Response findCategorySkills(@PathParam("category_id") final UUID category_id,
            @QueryParam("sort") final String sort,
            @QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        return Response.ok(service.findCategorySkills(category_id, sort, offset, limit)).build();
    }

    /*
     * @POST
     * 
     * @Path("{category_id}/skills/{skill_id}")
     * 
     * @Operation(summary = "insertCategorySkill")
     * public Response insertCategorySkill(@PathParam("category_id") UUID
     * category_id, @PathParam("skill_id") UUID skill_id) {
     * return Response.ok(service.insertCategorySkill(category_id,
     * skill_id)).build();
     * }
     * 
     * @DELETE
     * 
     * @Path("{category_id}/skills/{skill_id}")
     * 
     * @Operation(summary = "deleteCategorySkill")
     * public Response deleteCategorySkill(@PathParam("category_id") UUID
     * category_id, @PathParam("skill_id") UUID skill_id) {
     * return Response.ok(service.deleteCategorySkill(category_id,
     * skill_id)).build();
     * }
     */

    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload a catalog category image.")
    public Response uploadImage(@FormParam("file") EntityPart part) throws Exception {
        final String object_id = service.uploadImage(
                part.getContent(),
                -1L,
                part.getMediaType());
        return Response
                .ok(object_id)
                .build();
    }

    @GET
    @Path("/image/{image_id}")
    @Operation(summary = "Download a catalog category image.")
    public Response downloadImage(@PathParam("image_id") String image_id) throws Exception {
        final CommonStorageProvider.CommonStorageObject object = service.downloadImage(image_id);
        return Response
                .ok(object.input)
                .header(HttpHeaders.CONTENT_TYPE, object.type)
                .header(HttpHeaders.CONTENT_LENGTH, object.size)
                .build();
    }

    @DELETE
    @Path("/image/{image_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a catalog category image.")
    public Response deleteImage(@PathParam("image_id") String image_id) throws Exception {
        service.deleteImage(image_id);
        return Response.ok().build();
    }

    @GET
    @Path("Return a count of catalog categories.")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "count")
    public Response count() {
        return Response.ok(String.valueOf(service.count()), MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("test")
    @Produces({ MediaType.TEXT_PLAIN })
    @Operation(summary = "Test the service.")
    public Response test() {
        return Response.ok(String.valueOf(service.test()), MediaType.TEXT_PLAIN).build();
    }

    private String expiresAt(int minutes) {
        return OffsetDateTime
                .now()
                .plusMinutes(minutes)
                .format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
