package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.interfaces.service.CatalogCategoryService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CatalogCategoryGQL {

    @Inject
    private CatalogCategoryService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(@Name("skill") CatalogCategory category) {
        service.insert(category);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("category") CatalogCategory category) {
        service.update(category);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") UUID id) {
        service.deleteById(id);
    }

    @Query("findById")
    @Description("findById")
    public CatalogCategory findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CatalogCategory> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByParentId")
    @Description("findAllByParentId")
    public List<CatalogCategory> findAllByParentId(@Name("parent_id") UUID parent_id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByParentId(parent_id, sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("findAllByTitleLike")
    public List<CatalogCategory> findAllByTitleLike(@Name("pattern") String pattern, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
