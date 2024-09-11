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

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Workflow deployments GraphQL endpoint.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@GraphQLApi
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowDeploymentsGQL {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private WorkflowDeploymentsService service;

    public WorkflowDeploymentsGQL() {
    }
    
    @Mutation("insert")
    @Description("Insert new workflow deployment")
    public UUID insert(@Name("deployment") final WorkflowDeployment deployment) throws Exception {
        return service.insert(deployment);
    }

    @Mutation("delete")
    @Description("Delete workflow deployment by id")
    public void delete(@Name("deployment_id") final UUID deployment_id) throws Exception {
        service.delete(deployment_id);
    }

    @Mutation("update")
    @Description("Update existing workflow deployment")
    public void update(@Name("deployment") final WorkflowDeployment deployment) throws Exception {
        service.update(deployment);
    }

    @Query("findById")
    @Description("Find workflow deployment by id")
    public WorkflowDeployment findById(@Name("deployment_id") final UUID deployment_id) {
        return service.findById(deployment_id).orElse(null);
    }

    @Query("findAll")
    @Description("Find all workflow deployments")
    public List<WorkflowDeployment> findAll(@Name("sort") final String sort, @Name("offset") final Integer offset,
            @Name("limit") final Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
