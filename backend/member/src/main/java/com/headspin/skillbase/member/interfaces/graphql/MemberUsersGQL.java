package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.interfaces.service.MemberUsersService;

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
 * Member users REST endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberUsersGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberUsersService service;

    public MemberUsersGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert member user")
    public UUID insert(@Name("user") MemberUser user) {
        return service.insert(user);
    }

    @Mutation("delete")
    @Description("Delete member user")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update member user")
    public void update(@Name("user") MemberUser user) {
        service.update(user);
    }

    @Query("findById")
    @Description("Find member user by ID")
    public MemberUser findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member users")
    public List<MemberUser> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
