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

import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
    @Description("Insert new workflow instance")
    public UUID insert(@Name("instance") final WorkflowInstance instance) {
        return service.insert(instance);
    }

    @Mutation("delete")
    @Description("Delete workflow instance by id")
    public void delete(@Name("instance_id") final UUID instance_id) {
        service.delete(instance_id);
    }

    @Mutation("update")
    @Description("Update existing workflow instance")
    public void update(@Name("instance") final WorkflowInstance instance) {
        service.update(instance);
    }

    @Query("findById")
    @Description("Find workflow instance by id")
    public WorkflowInstance findById(@Name("instance_id") final UUID instance_id) {
        return service.findById(instance_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow instances")
    public List<WorkflowInstance> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
