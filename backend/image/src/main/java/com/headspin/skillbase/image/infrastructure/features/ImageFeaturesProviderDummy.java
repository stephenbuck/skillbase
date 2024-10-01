package com.headspin.skillbase.image.infrastructure.features;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageFeaturesProviderDummy extends CommonFeaturesProviderDummy {

    @Inject
    public ImageFeaturesProviderDummy() {
    }
}
