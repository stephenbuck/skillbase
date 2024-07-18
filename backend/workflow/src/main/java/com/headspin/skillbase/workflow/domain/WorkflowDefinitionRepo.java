package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow definition entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface WorkflowDefinitionRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDefinition definition);

    @Transactional
    public boolean delete(@NotNull UUID id);

    @Transactional
    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition);

    public Optional<WorkflowDefinition> findById(@NotNull UUID id);

    public Optional<WorkflowDefinition> findByCredentialId(@NotNull UUID credential_id);

    public List<WorkflowDefinition> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}