package com.headspin.skillbase.member.infrastructure.etcd;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.member.providers.MemberConfigProvider;

public class MemberConfigProviderEtcd implements MemberConfigProvider {

    private Config config;

    public MemberConfigProviderEtcd() {
        config = ConfigProvider.getConfig();
    }
    
    @Override
    public String getValue(String key) {
        return config.getValue(key, String.class);
    }
}
