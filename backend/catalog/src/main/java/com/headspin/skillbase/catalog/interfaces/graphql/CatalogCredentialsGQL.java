package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCredentialService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class CatalogCredentialsGQL {

    @Inject
    private CatalogCredentialService service;

    public CatalogCredentialsGQL() {
    }
    
    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("credential") CatalogCredential credential) {
        return service.insert(credential);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("credential") CatalogCredential credential) {
        service.update(credential);
    }

    @Query("findById")
    @Description("findById")
    public CatalogCredential findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CatalogCredential> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("findAllByTitleLike")
    public List<CatalogCredential> findAllByTitleLike(@Name("pattern") String pattern, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
