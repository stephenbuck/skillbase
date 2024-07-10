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

import com.headspin.groupbase.workflow.domain.WorkflowTask;
import com.headspin.groupbase.workflow.domain.WorkflowTaskRepo;

@RequestScoped
public class WorkflowTaskRepoJPA implements WorkflowTaskRepo {

    @PersistenceContext(name = "groupbase_workflow")
    private EntityManager em;

    public WorkflowTaskRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowTask task) {
        em.persist(task);
        return task.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public WorkflowTask update(@NotNull @Valid WorkflowTask task) {
        return em.merge(task);
    }

    @Override
    public Optional<WorkflowTask> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowTask.class, id));
    }

    @Override
    public List<WorkflowTask> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT t FROM WorkflowTask t ORDER BY :sort", WorkflowTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowTask> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT t FROM WorkflowTask t WHERE t.group_id = :groupId ORDER BY :sort", WorkflowTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowTask> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT t FROM WorkflowTask t WHERE t.user_id = :userId ORDER BY :sort", WorkflowTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowTask t", Long.class)
                .getSingleResult().longValue();
    }
}
