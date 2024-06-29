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

import com.headspin.skillbase.certify.domain.CertifyModel;
import com.headspin.skillbase.certify.domain.CertifyModelRepo;

@Slf4j
@RequestScoped
public class CertifyModelRepoJPA implements CertifyModelRepo {

    @PersistenceContext(name = "skillbase_certify")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CertifyModel model) {
        log.info("insert()");
        em.persist(model);
        return model.id();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public CertifyModel update(@NotNull @Valid CertifyModel model) {
        log.info("update({})", model.id());
        return em.merge(model);
    }

    @Override
    public Optional<CertifyModel> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CertifyModel.class, id));
    }

    @Override
    public List<CertifyModel> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT m FROM certify_model m ORDER BY :sort", CertifyModel.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyModel> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllBySkillId({})", skillId);
        return em
                .createQuery("SELECT m FROM certify_model m WHERE m.skill_id = :skillId ORDER BY :sort",
                        CertifyModel.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<CertifyModel> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId({})", userId);
        return em
                .createQuery("SELECT m FROM certify_model m WHERE m.user_id = :userId ORDER BY :sort",
                        CertifyModel.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
