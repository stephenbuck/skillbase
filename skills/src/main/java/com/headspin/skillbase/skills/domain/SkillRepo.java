package com.headspin.skillbase.skills.domain;

import java.util.List;
import java.util.Optional;

public interface SkillRepo {

    void insert(Skill skill);

    void delete(Skill skill);

    void deleteById(int id);

    Skill update(Skill skill);

    Optional<Skill> findById(int id);

    List<Skill> findAll();
}