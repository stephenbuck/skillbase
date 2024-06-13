package com.headspin.skillbase.members.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.members.domain.Member;
import com.headspin.skillbase.members.domain.MemberRepo;
import com.headspin.skillbase.members.domain.MemberSkill;

@RequestScoped
public class MemberRepoJPA implements MemberRepo {

    private static final Logger logger = Logger.getLogger(MemberRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-members")
    private EntityManager em;

    public void insert(Member member) {
        logger.info("insert()");
        em.persist(member);
    }

    public void delete(Member member) {
        logger.info("delete()");
        em.remove(member);
    }

    public void deleteById(int id) {
        logger.info("deleteById(" + id + ")");
        em.remove(em.find(Member.class, id));
    }

    public Member update(Member member) {
        logger.info("update()");
        return em.merge(member);
    }

    public Optional<Member> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT m FROM member m", Member.class).getResultList();
    }

    public Optional<MemberSkill> findMemberSkillById(int id) {
        logger.info("findMemberSkillById(" + id + ")");
        return Optional.ofNullable(em.find(MemberSkill.class, id));
    }

    public List<MemberSkill> findMemberSkillsById(int id) {
        logger.info("findMemberSkillsById(" + id + ")");
        return em.createQuery("SELECT ms FROM member_skill ms WHERE ms.member_id = :id", MemberSkill.class).getResultList();
    }
}
