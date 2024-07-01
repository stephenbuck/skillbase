package com.headspin.skillbase.identity.infrastructure.etcd;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.identity.providers.IdentityConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/*
 * IdentityConfigEtcd implements the IdentityConfig
 * interface using etcd.
 */

@Slf4j
@ApplicationScoped
public class IdentityConfigProviderEtcd implements IdentityConfigProvider {

    private Config config;

    public IdentityConfigProviderEtcd() {
        config = ConfigProvider.getConfig();
    }

    @Override
    public String getStringValue(String key) {
        return config.getValue(key, String.class);
    }
}
