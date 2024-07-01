package com.headspin.skillbase.identity.infrastructure.flagd;

import com.headspin.skillbase.identity.providers.IdentityFeatureProvider;

import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;

/*
 * IdentityFeatureProviderFlagd implements the IdentityFeatureProvider
 * interface using the Flagd tool.
 */

public class IdentityFeatureProviderFlagd implements IdentityFeatureProvider {

    private Client client;

    public IdentityFeatureProviderFlagd() {
        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        FlagdOptions flagdOptions = FlagdOptions.builder().offlineFlagSourcePath("/etc/flagd")
                .resolverType(Config.Resolver.IN_PROCESS).host("127.0.0.0").port(8013).build();
        FlagdProvider flagdProvider = new FlagdProvider(flagdOptions);
        api.setProviderAndWait(flagdProvider);
        client = api.getClient();
    }

    public String getValue(String key) {
        return client.getStringValue(key, "<DEFAULT>");
    }
}
