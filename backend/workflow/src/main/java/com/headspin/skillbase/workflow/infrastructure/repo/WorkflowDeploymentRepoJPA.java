package com.headspin.skillbase.workflow.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.Retry;

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
    public UUID insert(@NotNull @Valid final WorkflowDeployment deployment) {
        em.persist(deployment);
        return deployment.deployment_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID deployment_id) {
        em.remove(em.find(WorkflowDeployment.class, deployment_id));
    }

    @Override
    @Transactional
    @Retry(retryOn = {OptimisticLockException.class}, maxRetries = 10, delay = 100)
    public WorkflowDeployment update(@NotNull @Valid final WorkflowDeployment deployment) {
        return em.merge(deployment);
    }

    @Override
    public Optional<WorkflowDeployment> findById(@NotNull final UUID deployment_id) {
        return Optional.ofNullable(em.find(WorkflowDeployment.class, deployment_id));
    }

    @Override
    public Optional<WorkflowDeployment> findBySkillId(@NotNull final UUID skill_id) {
        return Optional.ofNullable(em.createQuery("SELECT d FROM WorkflowDeployment d WHERE d.skill_id = :skill_id LIMIT 1", WorkflowDeployment.class)
                .setParameter("skill_id", skill_id)
                .getSingleResult());
    }

    @Override
    public List<WorkflowDeployment> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT d FROM WorkflowDeployment d ORDER BY :sort", WorkflowDeployment.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "deployment_id"))
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
