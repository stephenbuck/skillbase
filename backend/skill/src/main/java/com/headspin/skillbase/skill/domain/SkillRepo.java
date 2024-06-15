package com.headspin.skillbase.skill.domain;

import org.jmolecules.ddd.types.Repository;

import java.util.List;
import java.util.Optional;

public interface SkillRepo extends Repository<Skill, SkillId> {

    void insert(Skill skill);

    void delete(Skill skill);

    void deleteById(SkillId id);

    Skill update(Skill skill);

    Optional<Skill> findById(SkillId id);

    List<Skill> findAll();
}