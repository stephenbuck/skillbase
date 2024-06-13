package com.headspin.skillbase.members.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import com.headspin.skillbase.members.domain.Member;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class MemberGraphQL {

    @Inject
    private MemberService service;
    
    @Mutation("insert")
    @Description("insert")
    public void insert(@Name("member") @Valid Member member) {
        service.insert(member);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") int id) {
        service.deleteById(id);
    }
    
    @Mutation("update")
    @Description("update")
    public void update(@Name("member") @Valid Member member) {
        service.update(member);
    }

    @Query("findById")
    @Description("findById")
    public Member findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<Member> findAll() {
        return service.findAll();
    }
}
