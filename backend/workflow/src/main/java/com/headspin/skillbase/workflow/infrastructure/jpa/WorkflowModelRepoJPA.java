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

import com.headspin.skillbase.workflow.domain.WorkflowModel;
import com.headspin.skillbase.workflow.domain.WorkflowModelRepo;

@RequestScoped
public class WorkflowModelRepoJPA implements WorkflowModelRepo {

    @PersistenceContext(name = "skillbase_workflow")
    private EntityManager em;

    public WorkflowModelRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowModel model) {
        em.persist(model);
        return model.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        em.remove(em.find(WorkflowModel.class, id));
    }

    @Override
    @Transactional
    public WorkflowModel update(@NotNull @Valid WorkflowModel model) {
        return em.merge(model);
    }

    @Override
    public Optional<WorkflowModel> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowModel.class, id));
    }

    @Override
    public List<WorkflowModel> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT g FROM WorkflowModel g ORDER BY :sort", WorkflowModel.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowModel s", Long.class)
                .getSingleResult().longValue();
    }
}
