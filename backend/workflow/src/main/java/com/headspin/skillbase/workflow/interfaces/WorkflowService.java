package com.headspin.skillbase.workflow.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowId;
import com.headspin.skillbase.workflow.domain.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowRepo;

import jakarta.inject.Inject;

public class WorkflowService {
    
    @Inject
    private WorkflowRepo repo;

    void insert(Workflow workflow) {
        repo.insert(workflow);
        WorkflowEvent.build("com.headspin.skillbase.workflow.inserted");
    }

    void delete(Workflow workflow) {
        repo.delete(workflow);
        WorkflowEvent.build("com.headspin.skillbase.workflow.deleted");
    }

    void deleteById(WorkflowId id) {
        repo.deleteById(id);
        WorkflowEvent.build("com.headspin.skillbase.workflow.deleted");
    }

    Workflow update(Workflow workflow) {
        Workflow updated = repo.update(workflow);
        WorkflowEvent.build("com.headspin.skillbase.workflow.updated");
        return updated;
    }
    
    public Optional<Workflow> findById(WorkflowId id) {
        return repo.findById(id);
    }

    public List<Workflow> findAll() {
        return repo.findAll();
    }
}
