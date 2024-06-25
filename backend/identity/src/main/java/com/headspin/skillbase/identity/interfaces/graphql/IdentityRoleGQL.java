package com.headspin.skillbase.identity.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.interfaces.service.IdentityRoleService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class IdentityRoleGQL {

    @Inject
    private IdentityRoleService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(
            @Name("role") IdentityRole role) {
        service.insert(role);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(
            @Name("id") UUID id) {
        service.deleteById(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(
            @Name("role") IdentityRole role) {
        service.update(role);
    }

    @Query("findById")
    @Description("findById")
    public IdentityRole findById(
            @Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<IdentityRole> findAll(
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByGroupId")
    @Description("findAllByGroupId")
    public List<IdentityRole> findAllByGroupId(
            @Name("id") UUID id,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAllByGroupId(id, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<IdentityRole> findAllByUserId(
            @Name("id") UUID id,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAllByUserId(id, sort, offset, limit);
    }
}
