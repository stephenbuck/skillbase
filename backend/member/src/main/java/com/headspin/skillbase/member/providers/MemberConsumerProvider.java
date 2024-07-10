package com.headspin.skillbase.member.providers;

import jakarta.transaction.Transactional;

public interface MemberConsumerProvider {

    @Transactional
    public void consume();
}
