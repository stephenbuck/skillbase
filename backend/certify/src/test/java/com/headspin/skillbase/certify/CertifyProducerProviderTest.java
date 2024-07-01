package com.headspin.skillbase.certify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.headspin.skillbase.certify.providers.CertifyProducerProvider;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CertifyProducerProviderTest {

    @Inject
    private CertifyProducerProvider provider;

    @BeforeAll
    public static void beforeAll() {
        log.info("workflow");
    }

    @Test
    public void testFlowable() {
        assertNotNull(provider, "Provider not found");
    }
}
