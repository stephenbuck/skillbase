package com.headspin.skillbase.catalog.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * CatalogApp is the main entry point.
 */

@RestController()
@RequestMapping("/catalog")
@SpringBootApplication
public class CatalogAppSpringBoot extends SpringBootServletInitializer {

    public CatalogAppSpringBoot() {
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(CatalogAppSpringBoot.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
    }
}
