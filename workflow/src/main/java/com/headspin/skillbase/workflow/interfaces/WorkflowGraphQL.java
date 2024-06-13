package com.headspin.skillbase.workflow.interfaces;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import com.headspin.skillbase.workflow.domain.Workflow;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class WorkflowGraphQL {

    @Inject
    private WorkflowService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(@Name("workflow") @Valid Workflow workflow) {
        service.insert(workflow);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") int id) {
        service.deleteById(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("workflow") @Valid Workflow workflow) {
        service.update(workflow);
    }

    @Query("findById")
    @Description("findById")
    public Workflow findById(@Name("id") int id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<Workflow> findAll() {
        return service.findAll();
    }
}
