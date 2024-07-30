package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Repository for workflow deployment entities.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowDeploymentRepo {

    public UUID insert(@NotNull @Valid WorkflowDeployment deployment);

    public void delete(@NotNull UUID id);

    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment);

    public Optional<WorkflowDeployment> findById(@NotNull UUID id);

    public Optional<WorkflowDeployment> findBySkillId(@NotNull UUID skill_id);

    public List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}