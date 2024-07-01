package com.headspin.skillbase.identity.providers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface IdentityConfigProvider {

    public String getStringValue(String key);
}
