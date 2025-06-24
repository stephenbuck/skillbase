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

    UUID insert(@NotNull @Valid WorkflowInstance instance);

    void delete(@NotNull UUID instance_id);

    WorkflowInstance update(@NotNull @Valid WorkflowInstance instance);

    Optional<WorkflowInstance> findById(@NotNull UUID instance_id);

    List<WorkflowInstance> findAll(String sort, Integer offset, Integer limit);

    Long count();
}