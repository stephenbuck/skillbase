package com.headspin.groupbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WorkflowCertRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowCert cert);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public WorkflowCert update(@NotNull @Valid WorkflowCert cert);

    public Optional<WorkflowCert> findById(@NotNull UUID id);

    public List<WorkflowCert> findAll(String sort, Integer offset, Integer limit);

    public List<WorkflowCert> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit);

    public List<WorkflowCert> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit);

    public Long count();
}