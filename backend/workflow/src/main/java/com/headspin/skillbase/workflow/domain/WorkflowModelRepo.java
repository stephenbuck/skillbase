package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WorkflowModelRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowModel group);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public WorkflowModel update(@NotNull @Valid WorkflowModel group);

    public Optional<WorkflowModel> findById(@NotNull UUID id);

    public List<WorkflowModel> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}