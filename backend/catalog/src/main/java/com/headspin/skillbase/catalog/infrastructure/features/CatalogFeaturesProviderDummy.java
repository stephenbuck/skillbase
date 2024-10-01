package com.headspin.skillbase.catalog.infrastructure.features;

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
public class CatalogFeaturesProviderDummy extends CommonFeaturesProviderDummy {

    @Inject
    public CatalogFeaturesProviderDummy() {
    }
}
