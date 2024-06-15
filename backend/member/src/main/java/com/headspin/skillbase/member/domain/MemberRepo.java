package com.headspin.skillbase.member.domain;

import org.jmolecules.ddd.types.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepo extends Repository<Member, MemberId> {

    void insert(Member member);

    void delete(Member member);

    void deleteById(MemberId id);

    Member update(Member member);

    Optional<Member> findById(MemberId id);

    List<Member> findAll();
}