package com.headspin.skillbase.workflow.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowRepo;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.inject.Inject;

public class WorkflowService {
    
    @Inject
    private WorkflowRepo repo;

    private CloudEvent buildWorkflowEvent(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }

    void insert(Workflow workflow) {
        repo.insert(workflow);
        buildWorkflowEvent("skillbase-workflow.workflow-inserted");
    }

    void delete(Workflow workflow) {
        repo.delete(workflow);
        buildWorkflowEvent("skillbase-workflow.workflow-inserted");
    }

    void deleteById(int id) {
        repo.deleteById(id);
        buildWorkflowEvent("skillbase-workflow.workflow-inserted");
    }

    Workflow update(Workflow workflow) {
        Workflow updated = repo.update(workflow);
        buildWorkflowEvent("skillbase-workflow.workflow-inserted");
        return updated;
    }
    
    public Optional<Workflow> findById(int id) {
        return repo.findById(id);
    }

    public List<Workflow> findAll() {
        return repo.findAll();
    }
}
