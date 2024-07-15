package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow deployment entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowDeploymentRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDeployment deployment);

    @Transactional
    public boolean delete(@NotNull UUID id);

    @Transactional
    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment);

    public Optional<WorkflowDeployment> findById(@NotNull UUID id);

    public List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}