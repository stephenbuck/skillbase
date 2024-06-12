package com.headspin.skillbase.members.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.members.domain.MemberSkill;
import com.headspin.skillbase.members.domain.MemberSkillRepo;

@RequestScoped
public class MemberSkillRepoJPA implements MemberSkillRepo {

    private static final Logger logger = Logger.getLogger(MemberSkillRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-members")
    private EntityManager em;

    public Optional<MemberSkill> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(MemberSkill.class, id));
    }

    public List<MemberSkill> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT ms FROM MemberSkill ms", MemberSkill.class).getResultList();
    }
}