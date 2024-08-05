package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

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
 * Workflow instances GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowInstancesGQL {

    @Inject
    private JsonWebToken jwt;
    
    @Inject
    private WorkflowInstancesService service;

    public WorkflowInstancesGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert workflow instance")
    public UUID insert(@Name("instance") WorkflowInstance instance) {
        return service.insert(instance);
    }

    @Mutation("delete")
    @Description("Delete workflow instance")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update workflow instance")
    public void update(@Name("instance") WorkflowInstance instance) {
        service.update(instance);
    }

    @Query("findById")
    @Description("Find workflow instance by ID")
    public WorkflowInstance findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow instances")
    public List<WorkflowInstance> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
