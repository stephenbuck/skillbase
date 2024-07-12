package com.headspin.skillbase.catalog.infrastructure.etcd;


import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogConfigProviderEtcd implements CatalogConfigProvider {

    public CatalogConfigProviderEtcd() {
    }
    
    @Override
    public Optional<String> getValue(String key, Class type) {

        try {
            Config conf = ConfigProvider.getConfig();
            Optional<String> value = conf.getOptionalValue(key, String.class);
            return value;
        }
        catch (Exception e) {
            log.info(String.valueOf(e));
        }

        return null;
    }
}
