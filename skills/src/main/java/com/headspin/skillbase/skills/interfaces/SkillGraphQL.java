package com.headspin.skillbase.skills.interfaces;

import jakarta.inject.Inject;

import com.headspin.skillbase.skills.domain.Skill;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class SkillGraphQL {

    @Inject
    private SkillService service;
    
    @Query("skill")
    public Skill findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
}
