package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow task entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowTaskRepo {

    public UUID insert(@NotNull @Valid WorkflowTask task);

    public boolean delete(@NotNull UUID id);

    public WorkflowTask update(@NotNull @Valid WorkflowTask task);

    public Optional<WorkflowTask> findById(@NotNull UUID id);

    public List<WorkflowTask> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}