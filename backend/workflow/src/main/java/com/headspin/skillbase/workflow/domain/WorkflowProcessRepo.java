package com.headspin.skillbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WorkflowProcessRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowProcess user);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public WorkflowProcess update(@NotNull @Valid WorkflowProcess user);

    public Optional<WorkflowProcess> findById(@NotNull UUID id);

    public List<WorkflowProcess> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}