package com.headspin.skillbase.certify.providers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CertifyFeatureProvider {

    public String getValue(String key);
}
