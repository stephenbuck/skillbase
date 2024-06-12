package com.headspin.skillbase.skills.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillsApp {

  private static final Logger logger = LogManager.getLogger(SkillsApp.class);

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(SkillsApp.class);
  }
}
