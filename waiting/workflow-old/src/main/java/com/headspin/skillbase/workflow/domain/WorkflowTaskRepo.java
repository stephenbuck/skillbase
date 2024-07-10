package com.headspin.groupbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WorkflowTaskRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowTask task);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public WorkflowTask update(@NotNull @Valid WorkflowTask task);

    public Optional<WorkflowTask> findById(@NotNull UUID id);

    public List<WorkflowTask> findAll(String sort, Integer offset, Integer limit);

    public List<WorkflowTask> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit);

    public List<WorkflowTask> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit);

    public Long count();
}