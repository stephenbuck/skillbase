package com.headspin.skillbase.certify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.headspin.skillbase.certify.providers.CertifyConfigProvider;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
public class CertifyConfigProviderTest {

    @Inject
    private CertifyConfigProvider provider;

    @BeforeAll
    public static void beforeAll() {
        log.info("config");
    }

    @Test
    public void testProvider() {
        assertNotNull(provider, "Provider not found");
    }

    @Test
    public void testConfig() {
        String message = provider.getValue("foo");
        log.info("foo = {}", message);
    }
}
