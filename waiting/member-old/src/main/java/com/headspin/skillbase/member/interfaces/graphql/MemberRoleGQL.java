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

import com.headspin.skillbase.member.domain.MemberRole;
import com.headspin.skillbase.member.interfaces.service.MemberRoleService;

/*
 * MemberRoleGQL implements a GraphQL interface for the
 * Role domain.
 */

@GraphQLApi
@ApplicationScoped
public class MemberRoleGQL {

    @Inject
    private MemberRoleService service;

    public MemberRoleGQL() {
    }

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("role") MemberRole role) {
        return service.insert(role);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("role") MemberRole role) {
        service.update(role);
    }

    @Query("findById")
    @Description("findById")
    public MemberRole findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<MemberRole> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByGroupId")
    @Description("findAllByGroupId")
    public List<MemberRole> findAllByGroupId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByGroupId(id, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<MemberRole> findAllByUserId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByUserId(id, sort, offset, limit);
    }
}
