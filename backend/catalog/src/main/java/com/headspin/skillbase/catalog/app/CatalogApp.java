package com.headspin.skillbase.catalog.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApp {

  private static final Logger logger = LoggerFactory.getLogger(CatalogApp.class);

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(CatalogApp.class);
  }
}
