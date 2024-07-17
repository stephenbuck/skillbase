package com.headspin.skillbase.member.providers;

import com.headspin.skillbase.common.events.MemberEvent;

import jakarta.transaction.Transactional;

public interface MemberProducerProvider {

    public void test();
    
    @Transactional
    public void produce(MemberEvent event);
}
