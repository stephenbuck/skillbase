package com.headspin.skillbase.skills.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.skills.domain.Skill;
import com.headspin.skillbase.skills.domain.SkillRepo;

import jakarta.inject.Inject;

public class SkillService {

    @Inject
    private SkillRepo repo;

    public Optional<Skill> findById(int id) {
        return repo.findById(id);
    }

    public List<Skill> findAll() {
        return repo.findAll();
    }
}
