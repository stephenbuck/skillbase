package com.headspin.skillbase.identity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
@ApplicationScoped
@AnalyzeClasses(packages = "com.headspin.skillbase.identity")
public class IdentityArchTest {

    @BeforeAll
    public static void beforeAll() {
        log.info("arch");
    }

    @ArchTest
    private final ArchRule classes_should_not_access_standard_streams_from_library = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private final ArchRule classes_should_not_use_java_util_logging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private final ArchRule xclasses_should_throw_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
}