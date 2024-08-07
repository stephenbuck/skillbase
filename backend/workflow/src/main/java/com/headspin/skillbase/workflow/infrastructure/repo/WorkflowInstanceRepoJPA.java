package com.headspin.skillbase.workflow.infrastructure.repo;

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

import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowInstanceRepo;

/**
 * JPA implementation of workflow instance repository interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@RequestScoped
public class WorkflowInstanceRepoJPA implements WorkflowInstanceRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowInstanceRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowInstance instance) {
        em.persist(instance);
        return instance.instance_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID instance_id) {
        em.remove(em.find(WorkflowInstance.class, instance_id));
    }

    @Override
    @Transactional
    public WorkflowInstance update(@NotNull @Valid WorkflowInstance instance) {
        return em.merge(instance);
    }

    @Override
    public Optional<WorkflowInstance> findById(@NotNull UUID instance_id) {
        return Optional.ofNullable(em.find(WorkflowInstance.class, instance_id));
    }

    @Override
    public List<WorkflowInstance> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT i FROM WorkflowInstance i ORDER BY :sort", WorkflowInstance.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "instance_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowInstance i", Long.class)
                .getSingleResult().longValue();
    }
}
