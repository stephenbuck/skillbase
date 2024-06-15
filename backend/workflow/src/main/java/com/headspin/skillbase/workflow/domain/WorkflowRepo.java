package com.headspin.skillbase.workflow.domain;

import org.jmolecules.ddd.types.Repository;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepo extends Repository {

    void insert(Workflow workflow);

    void delete(Workflow workflow);

    void deleteById(WorkflowId id);

    Workflow update(Workflow workflow);
    
    Optional<Workflow> findById(WorkflowId id);

    List<Workflow> findAll();
}
