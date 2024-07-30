package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow definition entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface WorkflowDefinitionRepo {

    public UUID insert(@NotNull @Valid WorkflowDefinition definition);

    public void delete(@NotNull UUID id);

    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition);

    public Optional<WorkflowDefinition> findById(@NotNull UUID id);

    public Optional<WorkflowDefinition> findByCredentialId(@NotNull UUID credential_id);

    public List<WorkflowDefinition> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}