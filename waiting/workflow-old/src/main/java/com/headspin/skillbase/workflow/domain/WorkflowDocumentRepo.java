package com.headspin.groupbase.workflow.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WorkflowDocumentRepo {

    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDocument document);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public WorkflowDocument update(@NotNull @Valid WorkflowDocument document);

    public Optional<WorkflowDocument> findById(@NotNull UUID id);

    public List<WorkflowDocument> findAll(String sort, Integer offset, Integer limit);

    public List<WorkflowDocument> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit);

    public List<WorkflowDocument> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit);

    public Long count();
}