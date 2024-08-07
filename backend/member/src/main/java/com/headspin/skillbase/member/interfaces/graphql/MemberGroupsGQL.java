package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupsService;

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
 * Member groups REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberGroupsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberGroupsService service;

    public MemberGroupsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new member group")
    public UUID insert(@Name("group") MemberGroup group) {
        return service.insert(group);
    }

    @Mutation("delete")
    @Description("Delete member group by id")
    public void delete(@Name("group_id") UUID group_id) {
        service.delete(group_id);
    }

    @Mutation("update")
    @Description("Update existing member group")
    public void update(@Name("group") MemberGroup group) {
        service.update(group);
    }

    @Query("findById")
    @Description("Find member group by id")
    public MemberGroup findById(@Name("group_id") UUID group_id) {
        return service.findById(group_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member groups")
    public List<MemberGroup> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
