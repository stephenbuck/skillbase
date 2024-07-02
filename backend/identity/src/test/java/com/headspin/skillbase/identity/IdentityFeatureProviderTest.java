package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.auto.AddPackages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.headspin.skillbase.identity.infrastructure.flagd.IdentityFeatureProviderFlagd;
import com.headspin.skillbase.identity.providers.IdentityFeatureProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Disabled

@Slf4j
@ApplicationScoped
@ExtendWith(WeldJunit5Extension.class)
@AddPackages(IdentityFeatureProvider.class)
@AddPackages(IdentityFeatureProviderFlagd.class)
public class IdentityFeatureProviderTest {

    // @Inject
    // private IdentityFeatureProvider provider;
    private IdentityFeatureProvider provider = new IdentityFeatureProviderFlagd();

    @BeforeAll
    public static void beforeAll() {
        log.info("feature");
    }

    @Test
    public void testProvider() {
        assertNotNull(provider, "Provider not found");
    }

    @Test
    public void testFeature() {

    }
}