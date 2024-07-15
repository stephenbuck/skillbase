package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL interface for workflow definitions.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
public class WorkflowDefinitionsGQL {

    @Inject
    private WorkflowDefinitionsService service;

    public WorkflowDefinitionsGQL() {
    }
    
    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("definition") WorkflowDefinition definition) {
        return service.insert(definition);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("definition") WorkflowDefinition definition) {
        service.update(definition);
    }

    @Query("findById")
    @Description("findById")
    public WorkflowDefinition findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<WorkflowDefinition> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
