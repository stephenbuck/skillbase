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

    UUID insert(@NotNull @Valid WorkflowDefinition definition);

    void delete(@NotNull UUID definition_id);

    WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition);

    Optional<WorkflowDefinition> findById(@NotNull UUID definition_id);

    Optional<WorkflowDefinition> findByCredentialId(@NotNull UUID credential_id);

    List<WorkflowDefinition> findAll(String sort, Integer offset, Integer limit);

    Long count();
}