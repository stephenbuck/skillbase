package com.headspin.skillbase.skill.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillApp {

  private static final Logger logger = LogManager.getLogger(SkillApp.class);

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(SkillApp.class);
  }
}
