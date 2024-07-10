package com.headspin.skillbase.workflow.infrastructure.jpa;

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

import com.headspin.skillbase.workflow.domain.WorkflowProcess;
import com.headspin.skillbase.workflow.domain.WorkflowProcessRepo;

@RequestScoped
public class WorkflowProcessRepoJPA implements WorkflowProcessRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowProcessRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowProcess user) {
        em.persist(user);
        return user.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        em.remove(em.find(WorkflowProcess.class, id));
    }

    @Override
    @Transactional
    public WorkflowProcess update(@NotNull @Valid WorkflowProcess user) {
        return em.merge(user);
    }

    @Override
    public Optional<WorkflowProcess> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowProcess.class, id));
    }

    @Override
    public List<WorkflowProcess> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT u FROM WorkflowProcess u ORDER BY :sort", WorkflowProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowProcess u", Long.class)
                .getSingleResult().longValue();
    }
}
