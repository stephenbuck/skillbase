package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.interfaces.service.MemberAchievementsService;

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
 * Member achievements REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberAchievementsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberAchievementsService service;

    public MemberAchievementsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new member achievement")
    public UUID insert(@Name("achievement") MemberAchievement achievement) {
        return service.insert(achievement);
    }

    @Mutation("delete")
    @Description("Delete member achievement by id")
    public void delete(@Name("achievement_id") UUID achievement_id) {
        service.delete(achievement_id);
    }

    @Mutation("update")
    @Description("Update existing member achievement")
    public void update(@Name("achievement") MemberAchievement achievement) {
        service.update(achievement);
    }

    @Query("findById")
    @Description("Find member achievement by id")
    public MemberAchievement findById(@Name("achievement_id") UUID achievement_id) {
        return service.findById(achievement_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member achievements")
    public List<MemberAchievement> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
