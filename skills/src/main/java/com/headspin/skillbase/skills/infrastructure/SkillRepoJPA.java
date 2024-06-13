package com.headspin.skillbase.skills.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.skills.domain.Skill;
import com.headspin.skillbase.skills.domain.SkillRepo;

@RequestScoped
public class SkillRepoJPA implements SkillRepo {
    
    private static final Logger logger = Logger.getLogger(SkillRepoJPA.class.getName());
    
    @PersistenceContext(name = "skillbase-skills")
    private EntityManager em;

    public void insert(Skill skill) {
        logger.info("insert()");
        em.persist(skill);
    }

    public void delete(Skill skill) {
        logger.info("delete()");
        em.remove(skill);
    }

    public void deleteById(int id) {
        logger.info("deleteById(" + id + ")");
        em.remove(em.find(Skill.class, id));
    }

    public Skill update(Skill skill) {
        logger.info("update()");
        return em.merge(skill);
    }

    public Optional<Skill> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Skill.class, id));
    }
  
    public List<Skill> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT s FROM Skill s", Skill.class).getResultList();
    }
  }
