package com.headspin.skillbase.workflow.interfaces;

import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.Workflow;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class WorkflowGraphQL {

    @Inject
    private WorkflowService service;

    @Query("workflow")
    public Workflow findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
}
