package com.headspin.skillbase.members.domain;

import java.util.List;
import java.util.Optional;

public interface MemberSkillRepo {

    Optional<MemberSkill> findById(int id);

    List<MemberSkill> findAll();
}