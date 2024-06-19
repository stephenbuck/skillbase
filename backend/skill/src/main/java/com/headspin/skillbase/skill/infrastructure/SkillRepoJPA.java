package com.headspin.skillbase.skill.infrastructure;

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

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillRepo;

@RequestScoped
public class SkillRepoJPA implements SkillRepo {

    private static final Logger logger = Logger.getLogger(SkillRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-skill")
    private EntityManager em;

    @Transactional
    public void insert(
            @NotNull @Valid Skill skill) {
        logger.info("insert(" + skill + ")");
        em.persist(skill);
    }

    @Transactional
    public Skill update(
            @NotNull @Valid Skill skill) {
        logger.info("update(" + skill + ")");
        return em.merge(skill);
    }

    @Transactional
    public void delete(
            @NotNull @Valid Skill skill) {
        logger.info("delete(" + skill + ")");
        em.remove(skill);
    }

    @Transactional
    public void deleteById(
            @NotNull UUID id) {
        logger.info("deleteById(" + id + ")");
        em.remove(em.find(Skill.class, id));
    }

    public Optional<Skill> findById(
            @NotNull UUID id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Skill.class, id));
    }

    public List<Skill> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAll()");
        return em.createQuery("SELECT s FROM skill s ORDER BY :sort", Skill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    public List<Skill> findAllByCategoryId(
            @NotNull UUID categoryId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllByCategoryId(" + categoryId + ")");
        return em.createQuery("SELECT s FROM skill s WHERE s.category_id = :categoryId ORDER BY :sort", Skill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    public List<Skill> findAllByTitleLike(
            @NotNull String pattern,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllByTitleLike(" + pattern + ")");
        return em.createQuery("SELECT s FROM skill s WHERE s.title LIKE ':pattern'", Skill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }
}
