package com.headspin.skillbase.workflow.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.workflow.domain.WorkflowTask;
import com.headspin.skillbase.workflow.domain.WorkflowTaskRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * JPA implementation of workflow task repository interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@RequestScoped
public class WorkflowTaskRepoJPA implements WorkflowTaskRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowTaskRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid final WorkflowTask task) {
        em.persist(task);
        return task.task_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID task_id) {
        em.remove(em.find(WorkflowTask.class, task_id));
    }

    @Override
    @Transactional
    public WorkflowTask update(@NotNull @Valid final WorkflowTask task) {
        return em.merge(task);
    }

    @Override
    public Optional<WorkflowTask> findById(@NotNull final UUID task_id) {
        return Optional.ofNullable(em.find(WorkflowTask.class, task_id));
    }

    @Override
    public List<WorkflowTask> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT t FROM WorkflowTask t ORDER BY :sort", WorkflowTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "task_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowTask t", Long.class)
                .getSingleResult().longValue();
    }
}
