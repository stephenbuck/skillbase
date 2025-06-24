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

    UUID insert(@NotNull @Valid WorkflowTask task);

    void delete(@NotNull UUID task_id);

    WorkflowTask update(@NotNull @Valid WorkflowTask task);

    Optional<WorkflowTask> findById(@NotNull UUID task_id);

    List<WorkflowTask> findAll(String sort, Integer offset, Integer limit);

    Long count();
}