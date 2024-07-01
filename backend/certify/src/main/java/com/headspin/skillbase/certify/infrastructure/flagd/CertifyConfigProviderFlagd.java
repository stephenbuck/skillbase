package com.headspin.skillbase.certify.infrastructure.flagd;

import com.headspin.skillbase.certify.providers.CertifyFeatureProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CertifyConfigProviderFlagd implements CertifyFeatureProvider {

    public CertifyConfigProviderFlagd() {
        log.info("flagd");
    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
