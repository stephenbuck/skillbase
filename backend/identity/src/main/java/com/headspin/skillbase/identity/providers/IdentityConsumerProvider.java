package com.headspin.skillbase.identity.providers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public interface IdentityConsumerProvider {

    @Transactional
    public void consume();
}