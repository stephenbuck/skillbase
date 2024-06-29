package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;

@Slf4j
public class IdentityConfigTest {

    @Test
    void demoTestMethod() {

        Config config = ConfigProvider.getConfig();
        log.info("config {}", config);

        String message = config.getValue("greeting.message", String.class);
        log.info("message = {}", message);
    }
}