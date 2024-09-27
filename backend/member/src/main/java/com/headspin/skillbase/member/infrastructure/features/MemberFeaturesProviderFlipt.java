package com.headspin.skillbase.member.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderFlipt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of member features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberFeaturesProviderFlipt extends CommonFeaturesProviderFlipt {

    @Inject
    public MemberFeaturesProviderFlipt(
            @ConfigProperty(name = "com.headspin.skillbase.member.features.flipt.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.member.features.flipt.namespace") final String configNamespace) {
        super(configUrl, configNamespace);
    }
}
