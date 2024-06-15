package com.headspin.skillbase.member.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.member.domain.Member;
import com.headspin.skillbase.member.domain.MemberId;
import com.headspin.skillbase.member.domain.MemberRepo;

@RequestScoped
public class MemberRepoJPA implements MemberRepo {

    private static final Logger logger = Logger.getLogger(MemberRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-member")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Member member) {
        logger.info("insert()");
        em.persist(member);
    }

    @Override
    @Transactional
    public void delete(Member member) {
        logger.info("delete()");
        em.remove(member);
    }

    @Override
    @Transactional
    public void deleteById(MemberId id) {
        logger.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public Member update(Member member) {
        logger.info("update()");
        return em.merge(member);
    }

    @Override
    public Optional<Member> findById(MemberId id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public List<Member> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT m FROM member m", Member.class).getResultList();
    }
}
