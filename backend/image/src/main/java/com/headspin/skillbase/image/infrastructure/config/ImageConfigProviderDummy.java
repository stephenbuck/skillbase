package com.headspin.skillbase.image.infrastructure.config;

import com.headspin.skillbase.common.infrastructure.config.CommonConfigProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageConfigProviderDummy extends CommonConfigProviderDummy {

    @Inject
    public ImageConfigProviderDummy() {
    }
}
