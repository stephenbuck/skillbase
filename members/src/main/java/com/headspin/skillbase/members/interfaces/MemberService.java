package com.headspin.skillbase.members.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.members.domain.Member;
import com.headspin.skillbase.members.domain.MemberRepo;
import com.headspin.skillbase.members.domain.MemberSkill;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.inject.Inject;

public class MemberService {

    @Inject
    private MemberRepo repo;

    private CloudEvent buildMemberEvent(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }

    void insert(Member member) {
        repo.insert(member);
        buildMemberEvent("skillbase-members.member-inserted");
    }

    void delete(Member member) {
        repo.delete(member);
        buildMemberEvent("skillbase-members.member-deleted");
    }

    void deleteById(int id) {
        repo.deleteById(id);
        buildMemberEvent("skillbase-members.member-deleted");
    }

    Member update(Member member) {
        Member updated = repo.update(member);
        buildMemberEvent("skillbase-members.member-updated");
        return updated;
    }

    public Optional<Member> findById(int id) {
        return repo.findById(id);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }

    public Optional<MemberSkill> findMemberSkillById(int id) {
        return repo.findMemberSkillById(id);
    }

    public List<MemberSkill> findMemberSkillsById(int id) {
        return repo.findMemberSkillsById(id);
    }
}
