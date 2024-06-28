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

import com.headspin.skillbase.certify.domain.CertifyCert;
import com.headspin.skillbase.certify.domain.CertifyCertRepo;

@Slf4j
@RequestScoped
public class CertifyCertRepoJPA implements CertifyCertRepo {

    @PersistenceContext(name = "skillbase_certify")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CertifyCert cert) {
        log.info("insert()");
        em.persist(cert);
        return cert.id();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public CertifyCert update(@NotNull @Valid CertifyCert cert) {
        log.info("update()");
        return em.merge(cert);
    }

    @Override
    public Optional<CertifyCert> findById(@NotNull UUID id) {
        log.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(CertifyCert.class, id));
    }

    @Override
    public List<CertifyCert> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT c FROM certify_cert c ORDER BY :sort", CertifyCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyCert> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllBySkillId(" + skillId + ")");
        return em
                .createQuery("SELECT c FROM certify_cert c WHERE c.skill_id = :skillId ORDER BY :sort",
                        CertifyCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyCert> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId(" + userId + ")");
        return em
                .createQuery("SELECT c FROM certify_cert c WHERE c.user_id = :userId ORDER BY :sort", CertifyCert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
