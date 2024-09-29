package com.headspin.skillbase.workflow.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderFlipt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of the common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class WorkflowFeaturesProviderFlipt extends CommonFeaturesProviderFlipt {

    @Inject
    public WorkflowFeaturesProviderFlipt(
            @ConfigProperty(name = "com.headspin.skillbase.workflow.features.flipt.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.features.flipt.namespace") final String configNamespace) {
        super(configUrl, configNamespace);
    }
}
