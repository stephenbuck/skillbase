package com.headspin.skillbase.cert.infrastructure;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.headspin.skillbase.cert.domain.Cert;
import com.headspin.skillbase.cert.domain.CertRepo;

@RequestScoped
public class CertRepoJPA implements CertRepo {

    private static final Logger logger = Logger.getLogger(CertRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-cert")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(
            @NotNull @Valid Cert cert) {
        logger.info("insert()");
        em.persist(cert);
    }

    @Override
    @Transactional
    public void delete(
            @NotNull @Valid Cert cert) {
        logger.info("delete()");
        em.remove(cert);
    }

    @Override
    @Transactional
    public Cert update(
            @NotNull @Valid Cert cert) {
        logger.info("update()");
        return em.merge(cert);
    }

    @Override
    @Transactional
    public void deleteById(
            @NotNull UUID id) {
        logger.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    public Optional<Cert> findById(
            @NotNull UUID id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Cert.class, id));
    }

    @Override
    public List<Cert> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAll()");
        return em.createQuery("SELECT c FROM cert c ORDER BY :sort", Cert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<Cert> findAllBySkillId(
            @NotNull UUID skillId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllBySkillId(" + skillId + ")");
        return em.createQuery("SELECT c FROM cert c WHERE c.skill_id = :skillId ORDER BY :sort", Cert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<Cert> findAllByUserId(
            @NotNull UUID userId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllByUserId(" + userId + ")");
        return em.createQuery("SELECT c FROM cert c WHERE c.user_id = :userId ORDER BY :sort", Cert.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }
}
