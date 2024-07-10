package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowProcess;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowProcessService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class WorkflowProcessesGQL {

    @Inject
    private WorkflowProcessService service;

    public WorkflowProcessesGQL() {
    }
    
    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("group") WorkflowProcess user) {
        return service.insert(user);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("user") WorkflowProcess user) {
        service.update(user);
    }

    @Query("findById")
    @Description("findById")
    public WorkflowProcess findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<WorkflowProcess> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
