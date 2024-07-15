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

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;

/**
 * JPA implementation of workflow definition repository interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@RequestScoped
public class WorkflowDefinitionRepoJPA implements WorkflowDefinitionRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowDefinitionRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDefinition definition) {
        em.persist(definition);
        return definition.id;
    }

    @Override
    @Transactional
    public boolean delete(@NotNull UUID id) {
        em.remove(em.find(WorkflowDefinition.class, id));
        return true;
    }

    @Override
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition) {
        return em.merge(definition);
    }

    @Override
    public Optional<WorkflowDefinition> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowDefinition.class, id));
    }

    @Override
    public List<WorkflowDefinition> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT d FROM WorkflowDefinition d ORDER BY :sort", WorkflowDefinition.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowDefinition d", Long.class)
                .getSingleResult().longValue();
    }
}
