package com.headspin.skillbase.certify.providers;

import com.headspin.skillbase.certify.domain.CertifyEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public interface CertifyProducerProvider {

    @Transactional
    public void produce(CertifyEvent event);
}
