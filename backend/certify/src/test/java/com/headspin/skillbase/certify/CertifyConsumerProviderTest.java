package com.headspin.skillbase.certify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CertifyConsumerProviderTest {
    
    @Inject
    private CertifyConsumerProviderTest provider;

    @BeforeAll
    public static void beforeAll() {
        log.info("consumer");
    }

    @Test
    public void testProvider() {
        assertNotNull(provider, "Provider not found");
    }
}
