package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.interfaces.service.MemberAchievementsService;

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
    private MemberAchievementsService service;

    public MemberAchievementsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert member achievement")
    public UUID insert(@Name("achievement") MemberAchievement achievement) {
        return service.insert(achievement);
    }

    @Mutation("delete")
    @Description("Delete member achievement")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update member achievement")
    public void update(@Name("achievement") MemberAchievement achievement) {
        service.update(achievement);
    }

    @Query("findById")
    @Description("Find member achievement by ID")
    public MemberAchievement findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member achievements")
    public List<MemberAchievement> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
