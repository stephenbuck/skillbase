package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepo {

    Optional<Workflow> findById(int id);

    List<Workflow> findAll();
}