package com.headspin.skillbase.skills.domain;

import java.util.List;
import java.util.Optional;

public interface SkillRepo {

    Optional<Skill> findById(int id);

    List<Skill> findAll();
}