package com.headspin.skillbase.member.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.interfaces.service.MemberUserService;

/*
 * MemberUserGQL implements a GraphQL interface for the
 * User domain.
 */

@GraphQLApi
@ApplicationScoped
public class MemberUserGQL {

    @Inject
    private MemberUserService service;

    public MemberUserGQL() {
    }

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("user") MemberUser user) {
        return service.insert(user);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("user") MemberUser user) {
        service.update(user);
    }

    @Query("findById")
    @Description("findById")
    public MemberUser findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<MemberUser> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByGroupId")
    @Description("findAllByGroupId")
    public List<MemberUser> findAllByGroupId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByGroupId(id, sort, offset, limit);
    }

    @Query("findAllByRoleId")
    @Description("findAllByRoleId")
    public List<MemberUser> findAllByRoleId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByRoleId(id, sort, offset, limit);
    }
}
