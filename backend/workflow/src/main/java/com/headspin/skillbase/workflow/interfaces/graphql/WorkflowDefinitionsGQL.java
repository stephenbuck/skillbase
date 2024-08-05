package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * Workflow definitions GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowDefinitionsGQL {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private WorkflowDefinitionsService service;

    public WorkflowDefinitionsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert workflow definition")
    public UUID insert(@Name("definition") WorkflowDefinition definition) {
        return service.insert(definition);
    }

    @Mutation("delete")
    @Description("Delete workflow definition")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update workflow definition")
    public void update(@Name("definition") WorkflowDefinition definition) {
        service.update(definition);
    }

    @Query("findById")
    @Description("Find workflow definition by ID")
    public WorkflowDefinition findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow definitions")
    public List<WorkflowDefinition> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
