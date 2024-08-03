package com.headspin.skillbase.catalog.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class CatalogSkillsGQL {

    @Inject
    private CatalogSkillsService service;

    public CatalogSkillsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert catalog skill")
    public UUID insert(@Name("skill") CatalogSkill skill) {
        return service.insert(skill);
    }

    @Mutation("delete")
    @Description("Delete catalog skill")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update catalog skill")
    public void update(@Name("skill") CatalogSkill skill) {
        service.update(skill);
    }

    @Query("findById")
    @Description("Find catalog skill by ID")
    public CatalogSkill findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all catalog skills")
    public List<CatalogSkill> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("Find all catalog skills by title")
    public List<CatalogSkill> findAllByTitleLike(@Name("pattern") String pattern, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
