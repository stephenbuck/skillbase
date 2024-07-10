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

import com.headspin.groupbase.workflow.domain.WorkflowCert;
import com.headspin.groupbase.workflow.domain.WorkflowCertRepo;

@RequestScoped
public class WorkflowCertRepoJPA implements WorkflowCertRepo {

    @PersistenceContext(name = "groupbase_workflow")
    private EntityManager em;

    public WorkflowCertRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowCert cert) {
        em.persist(cert);
        return cert.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public WorkflowCert update(@NotNull @Valid WorkflowCert cert) {
        return em.merge(cert);
    }

    @Override
    public Optional<WorkflowCert> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowCert.class, id));
    }

    @Override
    public List<WorkflowCert> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT c FROM WorkflowCert c ORDER BY :sort", WorkflowCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<WorkflowCert> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT c FROM WorkflowCert c WHERE c.group_id = :groupId ORDER BY :sort",
                        WorkflowCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowCert> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT c FROM WorkflowCert c WHERE c.user_id = :userId ORDER BY :sort", WorkflowCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowCert s", Long.class)
                .getSingleResult().longValue();
    }
}
