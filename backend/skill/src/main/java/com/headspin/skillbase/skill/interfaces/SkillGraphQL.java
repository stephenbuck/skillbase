package com.headspin.skillbase.skill.interfaces;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillId;

import java.util.List;

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
    public void insert(@Name("skill") @Valid Skill skill) {
        service.insert(skill);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") SkillId id) {
        service.deleteById(id);
    }
    
    @Mutation("update")
    @Description("update")
    public void update(@Name("skill") @Valid Skill skill) {
        service.update(skill);
    }

    @Query("findById")
    @Description("findById")
    public Skill findById(@Name("id") SkillId id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<Skill> findAll() {
        return service.findAll();
    }
}
