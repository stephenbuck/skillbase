package com.headspin.skillbase.members.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.members.domain.Member;
import com.headspin.skillbase.members.domain.MemberRepo;

@RequestScoped
public class MemberRepoJPA implements MemberRepo {

    private static final Logger logger = Logger.getLogger(MemberRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-members")
    private EntityManager em;

    public Optional<Member> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}