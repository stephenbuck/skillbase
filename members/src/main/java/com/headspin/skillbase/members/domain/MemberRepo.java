package com.headspin.skillbase.members.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {

    Optional<Member> findById(int id);

    List<Member> findAll();
}