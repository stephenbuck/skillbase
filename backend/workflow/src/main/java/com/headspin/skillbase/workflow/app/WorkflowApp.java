package com.headspin.skillbase.workflow.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkflowApp {

  private static final Logger logger = LogManager.getLogger(WorkflowApp.class);

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(WorkflowApp.class);
  }
}
