package com.headspin.skillbase.member.providers;

import com.headspin.skillbase.common.events.MemberEvent;

public interface MemberProducerProvider {

    public void test();
    
    public void produce(MemberEvent event);
}
