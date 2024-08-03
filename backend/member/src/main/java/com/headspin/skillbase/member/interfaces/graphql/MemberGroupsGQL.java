package com.headspin.skillbase.member.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.interfaces.service.MemberGroupsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class MemberGroupsGQL {

    @Inject
    private MemberGroupsService service;

    public MemberGroupsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert member group")
    public UUID insert(@Name("group") MemberGroup group) {
        return service.insert(group);
    }

    @Mutation("delete")
    @Description("Delete member group")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update member group")
    public void update(@Name("group") MemberGroup group) {
        service.update(group);
    }

    @Query("findById")
    @Description("Find member group by ID")
    public MemberGroup findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all member groups")
    public List<MemberGroup> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
