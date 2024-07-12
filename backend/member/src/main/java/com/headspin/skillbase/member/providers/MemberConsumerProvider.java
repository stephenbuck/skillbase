package com.headspin.skillbase.member.providers;

import jakarta.transaction.Transactional;

public interface MemberConsumerProvider {

    public void test();
    
    @Transactional
    public void consume();
}
