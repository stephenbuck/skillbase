package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.extension.ExtendWith;

import jakarta.enterprise.context.ApplicationScoped;

// @Disabled

@Slf4j
@ApplicationScoped
@ExtendWith(WeldJunit5Extension.class)
public class IdentityHealthTest {

    @BeforeAll
    public static void beforeAll() {
        log.info("health");
    }
}