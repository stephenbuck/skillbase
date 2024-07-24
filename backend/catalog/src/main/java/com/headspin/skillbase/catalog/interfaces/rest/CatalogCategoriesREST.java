package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TransactionRequiredException;
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
import jakarta.ws.rs.core.Response;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoryService;

@Path("categories")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CatalogCategoriesREST {

    @Inject
    private CatalogCategoryService service;

    public CatalogCategoriesREST() {
    }

    /*
     * @ApiResponses(value = {
     * 
     * @ApiResponse(responseCode = "400", description = "Invalid category"),
     * 
     * @ApiResponse(responseCode = "400", description = "Category already exists"),
     * 
     * @ApiResponse(responseCode = "500", description = "Internal server error")})
     */
    @PUT
    @Operation(summary = "Insert catalog category")
    public Response insert(CatalogCategory category) {
        try {
            UUID id = service.insert(category);
            return Response.ok(id).build();
        } catch (EntityExistsException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                            Problem.builder()
                                    .withType(URI.create("https://example.org/not-found"))
                                    .withTitle("Invalid category supplied")
                                    .withStatus(Status.BAD_REQUEST)
                                    .withDetail("TBD")
                                    .build())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                            Problem.builder()
                                    .withType(URI.create("https://example.org/not-found"))
                                    .withTitle("Invalid category supplied")
                                    .withStatus(Status.BAD_REQUEST)
                                    .withDetail("TBD")
                                    .build())
                    .build();
        } catch (TransactionRequiredException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                            Problem.builder()
                                    .withType(URI.create("https://example.org/not-found"))
                                    .withTitle("Transaction required")
                                    .withStatus(Status.INTERNAL_SERVER_ERROR)
                                    .withDetail("TBD")
                                    .build())
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete catalog category")
    public Response deleteById(@PathParam("id") UUID id) {
        return Response.ok(service.delete(id)).build();
    }

    @POST
    @Operation(summary = "Update catalog category")
    public Response update(CatalogCategory category) {
        return Response.ok(service.update(category)).build();
    }

    @GET
    @Operation(summary = "Find all catalog categories")
    public Response findAll(@QueryParam("sort") String sort, @QueryParam("offset") Integer offset,
            @QueryParam("limit") Integer limit) {
        return Response.ok(service.findAll(sort, offset, limit)).build();
    }

    /*
     * @ApiResponses(value = {
     * 
     * @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
     * 
     * @ApiResponse(responseCode = "404", description = "Category not found"),
     * 
     * @ApiResponse(responseCode = "500", description = "Internal server error")})
     */
    @GET
    @Path("{id}")
    @Operation(summary = "Find catalog category by ID")
    public Response findById(@PathParam("id") UUID id) {
        try {
            Optional<CatalogCategory> match = service.findById(id);
            return Response
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(match.get())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                            Problem.builder()
                                    .withType(URI.create("https://example.org/not-found"))
                                    .withTitle("Invalid ID supplied")
                                    .withStatus(Status.NOT_FOUND)
                                    .withDetail("id = " + id)
                                    .build())
                    .build();
        } catch (NoSuchElementException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                            Problem.builder()
                                    .withType(URI.create("https://example.org/not-found"))
                                    .withTitle("Category not found")
                                    .withStatus(Status.NOT_FOUND)
                                    .withDetail("id = " + id)
                                    .build())
                    .build();
        }
    }

    @GET
    @Path("{id}/categories")
    @Operation(summary = "Find all catalog category subcategories")
    public Response findCategoryCategories(@PathParam("id") UUID id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findCategoryCategories(id, sort, offset, limit)).build();
    }

    @POST
    @Path("{id}/categories/{category_id}")
    @Operation(summary = "Insert catalog category subcategory")
    public Response insertCategoryCategory(@PathParam("id") UUID id, @PathParam("category_id") UUID category_id) {
        return Response.ok(service.insertCategoryCategory(id, category_id)).build();
    }

    @DELETE
    @Path("{id}/categories/{category_id}")
    @Operation(summary = "Delete catalog category subcategory")
    public Response deleteCategoryCategory(@PathParam("id") UUID id, @PathParam("category_id") UUID category_id) {
        return Response.ok(service.deleteCategoryCategory(id, category_id)).build();
    }

    @GET
    @Path("{id}/skills")
    @Operation(summary = "Find all catalog category skills")
    public Response findCategorySkills(@PathParam("id") UUID id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findCategorySkills(id, sort, offset, limit)).build();
    }

    /*
     * @POST
     * 
     * @Path("{id}/skills/{skill_id}")
     * 
     * @Operation(summary = "insertCategorySkill")
     * public Response insertCategorySkill(@PathParam("id") UUID
     * id, @PathParam("skill_id") UUID skill_id) {
     * return Response.ok(service.insertCategorySkill(id, skill_id)).build();
     * }
     * 
     * @DELETE
     * 
     * @Path("{id}/skills/{skill_id}")
     * 
     * @Operation(summary = "deleteCategorySkill")
     * public Response deleteCategorySkill(@PathParam("id") UUID
     * id, @PathParam("skill_id") UUID skill_id) {
     * return Response.ok(service.deleteCategorySkill(id, skill_id)).build();
     * }
     */

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
