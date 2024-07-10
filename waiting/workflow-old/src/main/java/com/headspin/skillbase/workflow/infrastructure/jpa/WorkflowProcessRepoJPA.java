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

import com.headspin.groupbase.workflow.domain.WorkflowProcess;
import com.headspin.groupbase.workflow.domain.WorkflowProcessRepo;

@RequestScoped
public class WorkflowProcessRepoJPA implements WorkflowProcessRepo {

    @PersistenceContext(name = "groupbase_workflow")
    private EntityManager em;

    public WorkflowProcessRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowProcess process) {
        em.persist(process);
        return process.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public WorkflowProcess update(@NotNull @Valid WorkflowProcess process) {
        return em.merge(process);
    }

    @Override
    public Optional<WorkflowProcess> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(WorkflowProcess.class, id));
    }

    @Override
    public List<WorkflowProcess> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT p FROM WorkflowProcess p ORDER BY :sort", WorkflowProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowProcess> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT p FROM WorkflowProcess p WHERE p.group_id = :groupId ORDER BY :sort",
                        WorkflowProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<WorkflowProcess> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT p FROM WorkflowProcess p WHERE p.user_id = :userId ORDER BY :sort", WorkflowProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM WorkflowProcess p", Long.class)
                .getSingleResult().longValue();
    }
}
