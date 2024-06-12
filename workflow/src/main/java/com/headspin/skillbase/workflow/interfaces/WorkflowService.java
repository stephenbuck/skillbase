package com.headspin.skillbase.workflow.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowRepo;

import jakarta.inject.Inject;

public class WorkflowService {
    
    @Inject
    private WorkflowRepo repo;

    public Optional<Workflow> findById(int id) {
        return repo.findById(id);
    }

    public List<Workflow> findAll() {
        return repo.findAll();
    }
}
