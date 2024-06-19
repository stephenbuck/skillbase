package com.headspin.skillbase.skill.interfaces;

import jakarta.inject.Inject;

import com.headspin.skillbase.skill.domain.Skill;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class SkillGraphQL {

    @Inject
    private SkillService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(
        @Name("skill") Skill skill) {
        service.insert(skill);
    }

    @Mutation("update")
    @Description("update")
    public void update(
        @Name("skill") Skill skill) {
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
    public Skill findById(
        @Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<Skill> findAll(
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByCategoryId")
    @Description("findAllByCategoryId")
    public List<Skill> findAllByCategoryId(
        @Name("category_id") UUID category_id,
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAllByCategoryId(category_id, sort, offset, limit);
    }    

    @Query("findAllByTitleLike")
    @Description("findAllByTitleLike")
    public List<Skill> findAllByTitleLike(
        @Name("pattern") String pattern,
        @Name("sort") String sort,
        @Name("offset") Integer offset,
        @Name("limit") Integer limit) {
        return service.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
