package com.headspin.skillbase.skills.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.skills.domain.Skill;
import com.headspin.skillbase.skills.domain.SkillRepo;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.inject.Inject;

public class SkillService {

    @Inject
    private SkillRepo repo;

    private CloudEvent buildSkillEvent(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }

    void insert(Skill skill) {
        repo.insert(skill);
        buildSkillEvent("skillbase-skills.skill-inserted");
    }

    void delete(Skill skill) {
        repo.delete(skill);
        buildSkillEvent("skillbase-skills.skill-deleted");
    }

    public void deleteById(int id) {
        repo.deleteById(id);
        buildSkillEvent("skillbase-skills.skill-deleted");
    }

    Skill update(Skill skill) {
        Skill updated = repo.update(skill);
        buildSkillEvent("skillbase-skills.skill-updated");
        return updated;
    }
    
    public Optional<Skill> findById(int id) {
        return repo.findById(id);
    }

    public List<Skill> findAll() {
        return repo.findAll();
    }
}
