package com.headspin.skillbase.identity.app;

import jakarta.enterprise.context.ApplicationScoped;

import dev.openfeature.sdk.OpenFeatureAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ApplicationScoped
@Configuration
public class IdentityAppFeature {

    @Bean
    public OpenFeatureAPI OpenFeatureAPI() {
        final OpenFeatureAPI openFeatureAPI = OpenFeatureAPI.getInstance();
        return openFeatureAPI;
    }
}
