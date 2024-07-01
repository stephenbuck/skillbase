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

import com.headspin.skillbase.certify.domain.CertifyDocument;
import com.headspin.skillbase.certify.domain.CertifyDocumentRepo;

@Slf4j
@RequestScoped
public class CertifyDocumentRepoJPA implements CertifyDocumentRepo {

    @PersistenceContext(name = "skillbase_certify")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CertifyDocument document) {
        log.info("insert()");
        em.persist(document);
        return document.id();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public CertifyDocument update(@NotNull @Valid CertifyDocument document) {
        log.info("update({})", document.id());
        return em.merge(document);
    }

    @Override
    public Optional<CertifyDocument> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CertifyDocument.class, id));
    }

    @Override
    public List<CertifyDocument> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT m FROM certify_document m ORDER BY :sort", CertifyDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyDocument> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllBySkillId({})", skillId);
        return em
                .createQuery("SELECT m FROM certify_document m WHERE m.skill_id = :skillId ORDER BY :sort",
                        CertifyDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyDocument> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId({})", userId);
        return em
                .createQuery("SELECT m FROM certify_document m WHERE m.user_id = :userId ORDER BY :sort",
                        CertifyDocument.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public Long count() {
        return 0L;
    }
}
