package com.headspin.skillbase.member.infrastructure.etcd;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.member.providers.MemberConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/*
 * MemberConfigEtcd implements the MemberConfig
 * interface using etcd.
 */

@Slf4j
public class MemberConfigProviderEtcd implements MemberConfigProvider {

    // private Config config;

    public MemberConfigProviderEtcd() {
       // config = ConfigProvider.getConfig();
    }

    @Override
    public String getStringValue(String key) {
        return null; // config.getValue(key, String.class);
    }
}
