package com.headspin.skillbase.user.app;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApp {

  private static final Logger logger = Logger.getLogger(UserApp.class.getName());

  public static void main(String[] args) {
    logger.info("main");
    SpringApplication.run(UserApp.class);
  }
}
