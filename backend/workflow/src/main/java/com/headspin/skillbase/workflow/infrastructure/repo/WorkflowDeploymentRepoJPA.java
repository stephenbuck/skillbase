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

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;

/**
 * JPA implementation of workflow deployment repository interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@RequestScoped
public class WorkflowDeploymentRepoJPA implements WorkflowDeploymentRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowDeploymentRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDeployment deployment) {
        em.persist(deployment);
        return deployment.id;
    }

    @Override
    @Transactional
    public boolean delete(@NotNull UUID id) {
        em.remove(em.find(WorkflowDeployment.class, id));
        return true;
    }

    @Override
    @Transactional
    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment) {
        return em.merge(deployment);
    }

    @Override
    public Optional<WorkflowDeployment> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowDeployment.class, id));
    }

    @Override
    public Optional<WorkflowDeployment> findBySkillId(@NotNull UUID skill_id) {
        return Optional.ofNullable(em.createQuery("SELECT d FROM WorkflowDeployment d WHERE d.skill_id = :skill_id LIMIT 1", WorkflowDeployment.class)
                .setParameter("skill_id", skill_id)
                .getSingleResult());
    }

    @Override
    public List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT d FROM WorkflowDeployment d ORDER BY :sort", WorkflowDeployment.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowDeployment d", Long.class)
                .getSingleResult().longValue();
    }
}
