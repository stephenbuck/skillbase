package com.headspin.skillbase.catalog.infrastructure.storage;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogStorageProviderDummy extends CommonStorageProviderDummy {

    @Inject
    public CatalogStorageProviderDummy() {
    }
}
