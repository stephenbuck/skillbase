package com.headspin.skillbase.members.interfaces;

import jakarta.inject.Inject;

import com.headspin.skillbase.members.domain.Member;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class MemberGraphQL {

    @Inject
    private MemberService service;
    
    @Query("member")
    public Member findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
}
