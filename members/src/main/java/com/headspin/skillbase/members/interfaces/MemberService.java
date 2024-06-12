package com.headspin.skillbase.members.interfaces;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.members.domain.Member;
import com.headspin.skillbase.members.domain.MemberRepo;

import jakarta.inject.Inject;

public class MemberService {
    
    @Inject
    private MemberRepo repo;

    public Optional<Member> findById(int id) {
        return repo.findById(id);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }
}
