package com.headspin.skillbase.identity.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Identity is the main entry point. It does basic configuration
 * and uses Spring Boot to launch.
 */

@SpringBootApplication
public class IdentityApp {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApp.class);
    }
}
