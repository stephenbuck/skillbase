package com.headspin.skillbase.identity;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import org.jboss.weld.junit5.WeldJunit5Extension;
//import org.jboss.weld.junit5.auto.AddPackages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.headspin.skillbase.identity.infrastructure.etcd.IdentityConfigProviderEtcd;
import com.headspin.skillbase.identity.providers.IdentityConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Disabled

@Slf4j
/*
@ApplicationScoped
@ExtendWith(WeldJunit5Extension.class)
@AddPackages(IdentityConfigProvider.class)
@AddPackages(IdentityConfigProviderEtcd.class)
*/
public class IdentityConfigProviderTest {
    /*

    // @Inject
    // private IdentityConfigProvider provider;
    private static IdentityConfigProviderEtcd provider = new IdentityConfigProviderEtcd();

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
        String message = provider.getStringValue("foo");
        log.info("foo = {}", message);
    }
        */
}