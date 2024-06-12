package com.headspin.skillbase.workflow.interfaces;

import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.Process;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class ProcessGraphQL {

    @Inject
    private ProcessService service;
    
    @Query("process")
    public Process findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
}
