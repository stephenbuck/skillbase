package com.headspin.skillbase.identity.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.interfaces.service.IdentityUserService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class IdentityUserGQL {

    @Inject
    private IdentityUserService service;

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("user") IdentityUser user) {
        return service.insert(user);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") UUID id) {
        service.deleteById(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("user") IdentityUser user) {
        service.update(user);
    }

    @Query("findById")
    @Description("findById")
    public IdentityUser findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<IdentityUser> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByGroupId")
    @Description("findAllByGroupId")
    public List<IdentityUser> findAllByGroupId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByGroupId(id, sort, offset, limit);
    }

    @Query("findAllByRoleId")
    @Description("findAllByRoleId")
    public List<IdentityUser> findAllByRoleId(@Name("id") UUID id, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByRoleId(id, sort, offset, limit);
    }
}
