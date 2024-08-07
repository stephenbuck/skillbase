package com.headspin.skillbase.workflow.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
    public UUID insert(@NotNull @Valid final WorkflowDefinition definition) {
        em.persist(definition);
        return definition.definition_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID definition_id) {
        em.remove(em.find(WorkflowDefinition.class, definition_id));
    }

    @Override
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid final WorkflowDefinition definition) {
        return em.merge(definition);
    }

    @Override
    public Optional<WorkflowDefinition> findById(@NotNull final UUID definition_id) {
        return Optional.ofNullable(em.find(WorkflowDefinition.class, definition_id));
    }

    @Override
    public Optional<WorkflowDefinition> findByCredentialId(@NotNull final UUID credential_id) {
        return Optional.ofNullable(em.createQuery("SELECT d FROM WorkflowDefinition d WHERE d.credential_id = :credential_id LIMIT 1", WorkflowDefinition.class)
                .setParameter("credential_id", credential_id)
                .getSingleResult());
    }

    @Override
    public List<WorkflowDefinition> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT d FROM WorkflowDefinition d ORDER BY :sort", WorkflowDefinition.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "credential_id"))
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
