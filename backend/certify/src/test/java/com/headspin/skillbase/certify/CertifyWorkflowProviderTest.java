package com.headspin.skillbase.certify;

import org.junit.jupiter.api.Test;

import com.headspin.skillbase.certify.providers.CertifyWorkflowProvider;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
public class CertifyWorkflowProviderTest {

    @Inject
    private CertifyWorkflowProvider provider;

    @BeforeAll
    public static void beforeAll() {
        log.info("workflow");
    }

    @Test
    void testProvider() {
        assertNotNull(provider, "Provider not found");
    }

    @Test
    public void testWorkflow() {
        provider.test();
    }
}