package com.headspin.skillbase.identity.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.interfaces.service.IdentityGroupService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class IdentityGroupGQL {

    @Inject
    private IdentityGroupService service;

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("group") IdentityGroup group) {
        return service.insert(group);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") UUID id) {
        service.deleteById(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("group") IdentityGroup group) {
        service.update(group);
    }

    @Query("findById")
    @Description("findById")
    public IdentityGroup findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<IdentityGroup> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByRoleId")
    @Description("findAllByRoleId")
    public List<IdentityGroup> findAllByRoleId(@Name("id") UUID roleId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByRoleId(roleId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<IdentityGroup> findAllByUserId(@Name("id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("limit") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
