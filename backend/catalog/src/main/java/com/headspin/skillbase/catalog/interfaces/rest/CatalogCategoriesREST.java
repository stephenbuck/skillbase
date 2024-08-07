package com.headspin.skillbase.catalog.interfaces.rest;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;
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
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoriesService;

/**
 * Catalog categories REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Path("categories")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogCategoriesREST {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCategoriesService service;

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
    @Operation(summary = "Insert new catalog category")
    public Response insert(CatalogCategory category) {
        try {
            UUID category_id = service.insert(category);
            return Response.ok(category_id).build();
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
    @Path("{category_id}")
    @Operation(summary = "Delete catalog category by id")
    public Response deleteById(@PathParam("category_id") UUID category_id) {
        service.delete(category_id);
        return Response.ok().build();
    }

    @POST
    @Operation(summary = "Update existing catalog category")
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
    @Path("{category_id}")
    @Operation(summary = "Find catalog category by id")
    public Response findById(@PathParam("category_id") UUID category_id) {
        try {
            Optional<CatalogCategory> match = service.findById(category_id);
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
                                    .withDetail("category_id = " + category_id)
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
                                    .withDetail("category_id = " + category_id)
                                    .build())
                    .build();
        }
    }

    @GET
    @Path("{category_id}/categories")
    @Operation(summary = "Find all catalog category subcategories")
    public Response findCategoryCategories(@PathParam("category_id") UUID category_id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
        return Response.ok(service.findCategoryCategories(category_id, sort, offset, limit)).build();
    }

    @POST
    @Path("{category_id}/categories/{subcategory_id}")
    @Operation(summary = "Insert catalog category subcategory")
    public Response insertCategoryCategory(@PathParam("category_id") UUID category_id, @PathParam("subcategory_id") UUID subcategory_id) {
        service.insertCategoryCategory(category_id, subcategory_id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{category_id}/categories/{subcategory_id}")
    @Operation(summary = "Delete catalog category subcategory")
    public Response deleteCategoryCategory(@PathParam("category_id") UUID category_id, @PathParam("subcategory_id") UUID subcategory_id) {
        service.deleteCategoryCategory(category_id, category_id);
        return Response.ok().build();
    }

    @GET
    @Path("{category_id}/skills")
    @Operation(summary = "Find all catalog category skills")
    public Response findCategorySkills(@PathParam("category_id") UUID category_id, @QueryParam("sort") String sort,
            @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) {
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
     * return Response.ok(service.insertCategorySkill(category_id, skill_id)).build();
     * }
     * 
     * @DELETE
     * 
     * @Path("{category_id}/skills/{skill_id}")
     * 
     * @Operation(summary = "deleteCategorySkill")
     * public Response deleteCategorySkill(@PathParam("category_id") UUID
     * category_id, @PathParam("skill_id") UUID skill_id) {
     * return Response.ok(service.deleteCategorySkill(category_id, skill_id)).build();
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
