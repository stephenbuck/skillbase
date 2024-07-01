package com.headspin.skillbase.identity.providers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface IdentityFeatureProvider {

    public String getValue(String key);
}
