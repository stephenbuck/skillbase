package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoriesService;

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
 * Catalog categories REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogCategoriesGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogCategoriesService service;

    public CatalogCategoriesGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new catalog category")
    public UUID insert(@Name("category") CatalogCategory category) {
        return service.insert(category);
    }

    @Mutation("delete")
    @Description("Delete catalog category by id")
    public void delete(@Name("category_id") UUID category_id) {
        service.delete(category_id);
    }

    @Mutation("update")
    @Description("Update existing catalog category")
    public void update(@Name("category") CatalogCategory category) {
        service.update(category);
    }

    @Query("findById")
    @Description("Find catalog category by id")
    public CatalogCategory findById(@Name("category_id") UUID category_id) {
        return service.findById(category_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all categories")
    public List<CatalogCategory> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("Find all categories by title")
    public List<CatalogCategory> findAllByTitleLike(@Name("pattern") String pattern, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
