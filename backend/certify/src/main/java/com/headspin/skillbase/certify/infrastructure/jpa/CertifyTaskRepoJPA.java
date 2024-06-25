package com.headspin.skillbase.certify.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.headspin.skillbase.certify.domain.CertifyTask;
import com.headspin.skillbase.certify.domain.CertifyTaskRepo;

@Slf4j
@RequestScoped
public class CertifyTaskRepoJPA implements CertifyTaskRepo {

    @PersistenceContext(name = "skillbase_certify")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(@NotNull @Valid CertifyTask task) {
        log.info("insert()");
        em.persist(task);
    }

    @Override
    @Transactional
    public void delete(@NotNull @Valid CertifyTask task) {
        log.info("delete()");
        em.remove(task);
    }

    @Override
    @Transactional
    public CertifyTask update(@NotNull @Valid CertifyTask task) {
        log.info("update()");
        return em.merge(task);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull UUID id) {
        log.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    public Optional<CertifyTask> findById(@NotNull UUID id) {
        log.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(CertifyTask.class, id));
    }

    @Override
    public List<CertifyTask> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT t FROM task t ORDER BY :sort", CertifyTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyTask> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllBySkillId(" + skillId + ")");
        return em.createQuery("SELECT t FROM task t WHERE t.skill_id = :skillId ORDER BY :sort", CertifyTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyTask> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId(" + userId + ")");
        return em.createQuery("SELECT t FROM task t WHERE t.user_id = :userId ORDER BY :sort", CertifyTask.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
