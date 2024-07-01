package com.headspin.skillbase.identity.providers;

import com.headspin.skillbase.identity.domain.IdentityEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@ApplicationScoped
public interface IdentityProducerProvider {

    @Transactional
    public void produce(@NotNull IdentityEvent event);
}