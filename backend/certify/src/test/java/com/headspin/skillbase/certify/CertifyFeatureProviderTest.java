package com.headspin.skillbase.certify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.headspin.skillbase.certify.providers.CertifyFeatureProvider;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CertifyFeatureProviderTest {

    @Inject
    private CertifyFeatureProvider provider;

    @BeforeAll
    public static void beforeAll() {
        log.info("feature");
    }

    @Test
    public void testProvider() {
        assertNotNull(provider, "Provider not found");
    }
}
