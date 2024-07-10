package com.headspin.groupbase.workflow.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.headspin.groupbase.workflow.domain.WorkflowDocument;
import com.headspin.groupbase.workflow.domain.WorkflowDocumentRepo;

@RequestScoped
public class WorkflowDocumentRepoJPA implements WorkflowDocumentRepo {

    @PersistenceContext(name = "groupbase_workflow")
    private EntityManager em;

    public WorkflowDocumentRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDocument document) {
        em.persist(document);
        return document.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public WorkflowDocument update(@NotNull @Valid WorkflowDocument document) {
        return em.merge(document);
    }

    @Override
    public Optional<WorkflowDocument> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowDocument.class, id));
    }

    @Override
    public List<WorkflowDocument> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT d FROM WorkflowDocument d ORDER BY :sort", WorkflowDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowDocument> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT d FROM WorkflowDocument d WHERE m.group_id = :groupId ORDER BY :sort",
                        WorkflowDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowDocument> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT d FROM WorkflowDocument d WHERE m.user_id = :userId ORDER BY :sort",
                        WorkflowDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowDocument d", Long.class)
                .getSingleResult().longValue();
    }
}
