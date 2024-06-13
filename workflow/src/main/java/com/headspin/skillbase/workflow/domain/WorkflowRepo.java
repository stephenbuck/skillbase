package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepo {

    void insert(Workflow workflow);

    void delete(Workflow workflow);

    void deleteById(int id);

    Workflow update(Workflow workflow);
    
    Optional<Workflow> findById(int id);

    List<Workflow> findAll();
}
