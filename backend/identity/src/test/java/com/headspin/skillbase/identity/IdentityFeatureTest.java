package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.exceptions.OpenFeatureError;

@Slf4j
public class IdentityFeatureTest {

    // @Test
    void demoTestMethod() {

        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        try {

            FlagdOptions flagdOptions = FlagdOptions.builder()

                    .offlineFlagSourcePath("/etc/flagd").resolverType(Config.Resolver.IN_PROCESS).host("127.0.0.0")
                    .port(8013).build();

            FlagdProvider flagdProvider = new FlagdProvider(flagdOptions);

            api.setProviderAndWait(flagdProvider);
        } catch (OpenFeatureError e) {
            throw new RuntimeException("Failed to set OpenFeature provider", e);
        }
        final Client client = api.getClient();
        final String name = client.getStringValue("background-color", "<DEFAULT>");
        log.info("name = {}", name);
    }
}