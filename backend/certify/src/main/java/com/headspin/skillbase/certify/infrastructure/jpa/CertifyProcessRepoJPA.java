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

import com.headspin.skillbase.certify.domain.CertifyProcess;
import com.headspin.skillbase.certify.domain.CertifyProcessRepo;

@Slf4j
@RequestScoped
public class CertifyProcessRepoJPA implements CertifyProcessRepo {

    @PersistenceContext(name = "skillbase_certify")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CertifyProcess process) {
        log.info("insert()");
        em.persist(process);
        return process.id();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public CertifyProcess update(@NotNull @Valid CertifyProcess process) {
        log.info("update({})", process.id());
        return em.merge(process);
    }

    @Override
    public Optional<CertifyProcess> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CertifyProcess.class, id));
    }

    @Override
    public List<CertifyProcess> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT p FROM certify_process p ORDER BY :sort", CertifyProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyProcess> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllBySkillId({})", skillId);
        return em
                .createQuery("SELECT p FROM certify_process p WHERE p.skill_id = :skillId ORDER BY :sort",
                        CertifyProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyProcess> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId({})", userId);
        return em.createQuery("SELECT p FROM process p WHERE p.user_id = :userId ORDER BY :sort", CertifyProcess.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return 0L;
    }
}
