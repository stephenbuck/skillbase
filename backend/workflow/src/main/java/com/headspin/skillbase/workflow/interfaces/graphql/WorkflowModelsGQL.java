package com.headspin.skillbase.workflow.interfaces.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.headspin.skillbase.workflow.domain.WorkflowModel;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowModelService;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@ApplicationScoped
public class WorkflowModelsGQL {

    @Inject
    private WorkflowModelService service;

    public WorkflowModelsGQL() {
    }
    
    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("skill") WorkflowModel skill) {
        return service.insert(skill);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("skill") WorkflowModel skill) {
        service.update(skill);
    }

    @Query("findById")
    @Description("findById")
    public WorkflowModel findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<WorkflowModel> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }
}
