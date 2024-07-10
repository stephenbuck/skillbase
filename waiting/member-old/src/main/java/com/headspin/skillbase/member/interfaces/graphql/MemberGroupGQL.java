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

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupService;

/*
 * MemberGroupGQL implements a GraphQL interface for the
 * Group domain.
 */

@GraphQLApi
@ApplicationScoped
public class MemberGroupGQL {

    @Inject
    private MemberGroupService service;

    public MemberGroupGQL() {
    }

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("group") MemberGroup group) {
        return service.insert(group);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("group") MemberGroup group) {
        service.update(group);
    }

    @Query("findById")
    @Description("findById")
    public MemberGroup findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<MemberGroup> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByRoleId")
    @Description("findAllByRoleId")
    public List<MemberGroup> findAllByRoleId(@Name("id") UUID roleId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByRoleId(roleId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<MemberGroup> findAllByUserId(@Name("id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
