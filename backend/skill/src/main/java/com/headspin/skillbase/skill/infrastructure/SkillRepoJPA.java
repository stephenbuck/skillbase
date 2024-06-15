package com.headspin.skillbase.skill.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillId;
import com.headspin.skillbase.skill.domain.SkillRepo;

@RequestScoped
public class SkillRepoJPA implements SkillRepo {
    
    private static final Logger logger = Logger.getLogger(SkillRepoJPA.class.getName());
    
    @PersistenceContext(name = "skillbase-skill")
    private EntityManager em;

    public void insert(Skill skill) {
        logger.info("insert()");
        em.persist(skill);
    }

    public void delete(Skill skill) {
        logger.info("delete()");
        em.remove(skill);
    }

    public void deleteById(SkillId id) {
        logger.info("deleteById(" + id + ")");
        em.remove(em.find(Skill.class, id));
    }

    public Skill update(Skill skill) {
        logger.info("update()");
        return em.merge(skill);
    }

    public Optional<Skill> findById(SkillId id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Skill.class, id));
    }
  
    public List<Skill> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT s FROM skill s", Skill.class).getResultList();
    }
  }
