package com.headspin.skillbase.member.providers;

import com.headspin.skillbase.member.domain.MemberEvent;

import jakarta.transaction.Transactional;

public interface MemberProducerProvider {

    @Transactional
    public void produce(MemberEvent event);
}
