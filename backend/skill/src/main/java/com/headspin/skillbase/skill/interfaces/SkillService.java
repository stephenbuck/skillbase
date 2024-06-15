package com.headspin.skillbase.skill.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillEvent;
import com.headspin.skillbase.skill.domain.SkillId;
import com.headspin.skillbase.skill.domain.SkillRepo;

import jakarta.inject.Inject;

public class SkillService {

    @Inject
    private SkillRepo repo;

    void insert(Skill skill) {
        repo.insert(skill);
        SkillEvent.build("com.headspin.skillbase.skill.inserted");
    }

    void delete(Skill skill) {
        repo.delete(skill);
        SkillEvent.build("com.headspin.skillbase.skill.deleted");
    }

    public void deleteById(SkillId id) {
        repo.deleteById(id);
        SkillEvent.build("com.headspin.skillbase.skill.deleted");
    }

    Skill update(Skill skill) {
        Skill updated = repo.update(skill);
        SkillEvent.build("com.headspin.skillbase.skill.updated");
        return updated;
    }
    
    public Optional<Skill> findById(SkillId id) {
        return repo.findById(id);
    }

    public List<Skill> findAll() {
        return repo.findAll();
    }
}
