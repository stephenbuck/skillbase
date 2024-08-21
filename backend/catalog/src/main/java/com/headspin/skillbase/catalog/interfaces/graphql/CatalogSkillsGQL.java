package com.headspin.skillbase.catalog.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.interfaces.service.CatalogSkillsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Catalog skills GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class CatalogSkillsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CatalogSkillsService service;

    public CatalogSkillsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new catalog skill")
    public UUID insert(@Name("skill") final CatalogSkill skill) {
        return service.insert(skill);
    }

    @Mutation("delete")
    @Description("Delete catalog skill by id")
    public void delete(@Name("skill_id") final UUID skill_id) {
        service.delete(skill_id);
    }

    @Mutation("update")
    @Description("Update existing catalog skill")
    public void update(@Name("skill") final CatalogSkill skill) {
        service.update(skill);
    }

    @Query("findById")
    @Description("Find catalog skill by id")
    public CatalogSkill findById(@Name("skill_id") final UUID skill_id) {
        return service.findById(skill_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all catalog skills")
    public List<CatalogSkill> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByTitleLike")
    @Description("Find all catalog skills by title")
    public List<CatalogSkill> findAllByTitleLike(@Name("pattern") final String pattern, @Name("sort") final String sort,
            @Name("offset") final Integer offset, @Name("limit") final Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
