package com.headspin.skillbase.workflow.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
    @Description("Insert new workflow definition")
    public UUID insert(@Name("definition") final WorkflowDefinition definition) throws Exception {
        return service.insert(definition);
    }

    @Mutation("delete")
    @Description("Delete workflow definition by id")
    public void delete(@Name("definition_id") final UUID definition_id) throws Exception {
        service.delete(definition_id);
    }

    @Mutation("update")
    @Description("Update existing workflow definition")
    public void update(@Name("definition") final WorkflowDefinition definition) throws Exception {
        service.update(definition);
    }

    @Query("findById")
    @Description("Find workflow definition by id")
    public WorkflowDefinition findById(@Name("definition_id") final UUID definition_id) {
        return service.findById(definition_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow definitions")
    public List<WorkflowDefinition> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
