package com.headspin.skillbase.member.providers;

import com.headspin.skillbase.member.domain.MemberEvent;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

public interface MemberProducerProvider {

    public void produce(@NotNull MemberEvent event);
}