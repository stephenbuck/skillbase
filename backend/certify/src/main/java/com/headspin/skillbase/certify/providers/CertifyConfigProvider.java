package com.headspin.skillbase.certify.providers;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CertifyConfigProvider {

    public String getValue(String key);
}
