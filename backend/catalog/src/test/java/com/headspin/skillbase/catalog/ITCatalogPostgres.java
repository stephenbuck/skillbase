package com.headspin.skillbase.catalog;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
@Testcontainers
public class ITCatalogPostgres {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    void test() {

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:15432/skillbase", "postgres",
                "postgres")) {
            log.info("con = {}", con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
