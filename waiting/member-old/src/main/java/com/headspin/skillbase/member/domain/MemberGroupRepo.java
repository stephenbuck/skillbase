package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

public interface MemberGroupRepo {

        @Transactional
        public UUID insert(@NotNull MemberGroup group);

        @Transactional
        public void delete(@NotNull UUID id);

        @Transactional
        public MemberGroup update(@NotNull MemberGroup group);

        public Optional<MemberGroup> findById(@NotNull UUID id);

        public List<MemberGroup> findAll(@NotNull String sort, Integer offset, Integer limit);

        public List<MemberGroup> findAllByRoleId(@NotNull UUID roleId, String sort, Integer offset,
                        Integer limit);

        public List<MemberGroup> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
                        Integer limit);

        public Long count();
}