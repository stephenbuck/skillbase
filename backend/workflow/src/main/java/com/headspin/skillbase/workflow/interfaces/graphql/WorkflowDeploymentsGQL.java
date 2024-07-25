package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL interface for workflow deployments.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
public class WorkflowDeploymentsGQL {

    @Inject
    private WorkflowDeploymentsService service;

    public WorkflowDeploymentsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert workflow deployment")
    public UUID insert(@Name("deployment") WorkflowDeployment deployment) {
        return service.insert(deployment);
    }

    @Mutation("delete")
    @Description("Delete workflow deployment")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("Update workflow deployment")
    public void update(@Name("deployment") WorkflowDeployment deployment) {
        service.update(deployment);
    }

    @Query("findById")
    @Description("Find workflow deployment by ID")
    public WorkflowDeployment findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow deployments")
    public List<WorkflowDeployment> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
