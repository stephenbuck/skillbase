package com.headspin.skillbase.identity;

import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
@ExtendWith(WeldJunit5Extension.class)
public class IdentityUnitTest {

    @BeforeAll
    public static void beforeAll() {
        log.info("unit");
    }
}