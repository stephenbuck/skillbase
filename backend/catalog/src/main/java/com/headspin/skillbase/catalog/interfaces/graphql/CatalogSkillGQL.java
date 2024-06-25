package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CatalogSkillGQL {

    @Inject
    private CatalogSkillService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(
        @Name("skill") CatalogSkill skill) {
        service.insert(skill);
    }

    @Mutation("update")
    @Description("update")
    public void update(
        @Name("skill") CatalogSkill skill) {
        service.update(skill);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(
        @Name("id") UUID id) {
        service.deleteById(id);
    }
    
    @Query("findById")
    @Description("findById")
    public CatalogSkill findById(
        @Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<CatalogSkill> findAll(
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByCategoryId")
    @Description("findAllByCategoryId")
    public List<CatalogSkill> findAllByCategoryId(
        @Name("category_id") UUID category_id,
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAllByCategoryId(category_id, sort, offset, limit);
    }    

    @Query("findAllByTitleLike")
    @Description("findAllByTitleLike")
    public List<CatalogSkill> findAllByTitleLike(
        @Name("pattern") String pattern,
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
