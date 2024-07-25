package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow instance entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface WorkflowInstanceRepo {

    public UUID insert(@NotNull @Valid WorkflowInstance instance);

    public boolean delete(@NotNull UUID id);

    public WorkflowInstance update(@NotNull @Valid WorkflowInstance instance);

    public Optional<WorkflowInstance> findById(@NotNull UUID id);

    public List<WorkflowInstance> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}