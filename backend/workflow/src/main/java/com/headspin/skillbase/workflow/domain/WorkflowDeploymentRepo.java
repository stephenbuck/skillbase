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

    UUID insert(@NotNull @Valid WorkflowDeployment deployment);

    void delete(@NotNull UUID deployment_id);

    WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment);

    Optional<WorkflowDeployment> findById(@NotNull UUID deployment_id);

    Optional<WorkflowDeployment> findBySkillId(@NotNull UUID skill_id);

    List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit);

    Long count();
}