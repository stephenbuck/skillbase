package com.headspin.skillbase.member.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Member groups GraphQL endpoint.
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
    @Description("Insert a member group.")
    public UUID insert(@Name("group") final MemberGroup group) throws Exception {
        return service.insert(group);
    }

    @Mutation("delete")
    @Description("Delete a member group.")
    public void delete(@Name("group_id") final UUID group_id) throws Exception {
        service.delete(group_id);
    }

    @Mutation("update")
    @Description("Update a member group.")
    public void update(@Name("group") final MemberGroup group) throws Exception {
        service.update(group);
    }

    @Query("findById")
    @Description("Find a member group by id.")
    public MemberGroup findById(@Name("group_id") final UUID group_id) throws Exception {
        return service.findById(group_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member groups.")
    public List<MemberGroup> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
