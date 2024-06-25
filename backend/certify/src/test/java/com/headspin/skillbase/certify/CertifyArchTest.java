package com.headspin.skillbase.certify;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

@AnalyzeClasses(packages = "com.headspin.skillbase.certify")
public class CertifyArchTest {

  @ArchTest
  private final ArchRule classes_should_not_access_standard_streams_from_library = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
}