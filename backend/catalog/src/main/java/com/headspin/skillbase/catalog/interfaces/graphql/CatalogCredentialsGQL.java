package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCredentialsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * Catalog credentials REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogCredentialsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCredentialsService service;

    public CatalogCredentialsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new catalog skill credential")
    public UUID insert(@Name("credential") CatalogCredential credential) {
        return service.insert(credential);
    }

    @Mutation("delete")
    @Description("Delete catalog skill credential by id")
    public void delete(@Name("credential_id") UUID credential_id) {
        service.delete(credential_id);
    }

    @Mutation("update")
    @Description("Update existing catalog skill credential")
    public void update(@Name("credential") CatalogCredential credential) {
        service.update(credential);
    }

    @Query("findById")
    @Description("Find catalog skill credential by id")
    public CatalogCredential findById(@Name("credential_id") UUID credential_id) {
        return service.findById(credential_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all catalog skill credentials")
    public List<CatalogCredential> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("Find all catalog skill credentials by title")
    public List<CatalogCredential> findAllByTitleLike(@Name("pattern") String pattern, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
