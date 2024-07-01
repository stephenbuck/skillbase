package com.headspin.skillbase.certify.infrastructure.etcd;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.certify.providers.CertifyConfigProvider;

@Slf4j
@ApplicationScoped
public class CertifyConfigProviderEtcd implements CertifyConfigProvider {

    public CertifyConfigProviderEtcd() {
        log.info("etcd");
    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
