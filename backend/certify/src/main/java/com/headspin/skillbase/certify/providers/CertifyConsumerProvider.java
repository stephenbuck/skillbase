package com.headspin.skillbase.certify.providers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public interface CertifyConsumerProvider {

    @Transactional
    public void consume();
}
