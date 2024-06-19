package com.headspin.skillbase.skill.app;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillApp {

  private static final Logger logger = Logger.getLogger(SkillApp.class.getName());

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(SkillApp.class);
  }
}
