package com.headspin.skillbase.members.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {

    void insert(Member member);

    void delete(Member member);

    void deleteById(int id);

    Member update(Member member);

    Optional<Member> findById(int id);

    List<Member> findAll();

    Optional<MemberSkill> findMemberSkillById(int id);

    List<MemberSkill> findMemberSkillsById(int id);
}