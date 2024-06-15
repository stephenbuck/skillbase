package com.headspin.skillbase.certificate.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CertificateApp {

  private static final Logger logger = LogManager.getLogger(CertificateApp.class);

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(CertificateApp.class);
  }
}
