package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.interfaces.service.MemberAchievementService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class MemberAchievementsGQL {

    @Inject
    private MemberAchievementService service;

    public MemberAchievementsGQL() {
    }
    
    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("achievement") MemberAchievement achievement) {
        return service.insert(achievement);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("achievement") MemberAchievement achievement) {
        service.update(achievement);
    }

    @Query("findById")
    @Description("findById")
    public MemberAchievement findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<MemberAchievement> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
