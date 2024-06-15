package com.headspin.skillbase.member.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.member.domain.Member;
import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.domain.MemberId;
import com.headspin.skillbase.member.domain.MemberRepo;

import jakarta.inject.Inject;

public class MemberService {

    @Inject
    private MemberRepo repo;

    void insert(Member member) {
        repo.insert(member);
        MemberEvent.build("com.headspin.skillbase.member.inserted");
    }

    void delete(Member member) {
        repo.delete(member);
        MemberEvent.build("com.headspin.skillbase.member.deleted");
    }

    void deleteById(MemberId id) {
        repo.deleteById(id);
        MemberEvent.build("com.headspin.skillbase.member.deleted");
    }

    Member update(Member member) {
        Member updated = repo.update(member);
        MemberEvent.build("com.headspin.skillbase.member.updated");
        return updated;
    }

    public Optional<Member> findById(MemberId id) {
        return repo.findById(id);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }
}
