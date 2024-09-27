package com.headspin.skillbase.catalog.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCredentialsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Catalog credentials GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
public class CatalogCredentialsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCredentialsService service;

    public CatalogCredentialsGQL() {
    }

    @Mutation("insert")
    @Description("Insert a catalog credential.")
    public UUID insert(@Name("credential") final CatalogCredential credential) throws Exception {
        return service.insert(credential);
    }

    @Mutation("delete")
    @Description("Delete a catalog credential.")
    public void delete(@Name("credential_id") final UUID credential_id) throws Exception {
        service.delete(credential_id);
    }

    @Mutation("update")
    @Description("Update a catalog credential.")
    public void update(@Name("credential") final CatalogCredential credential) throws Exception {
        service.update(credential);
    }

    @Query("findById")
    @Description("Find a catalog credential by id.")
    public CatalogCredential findById(@Name("credential_id") final UUID credential_id) throws Exception {
        return service.findById(credential_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all catalog credentials.")
    public List<CatalogCredential> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("Find all catalog credentials by title")
    public List<CatalogCredential> findAllByTitleLike(@Name("pattern") final String pattern,
            @Name("sort") final String sort,
            @Name("offset") final Integer offset, @Name("limit") final Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
