package com.headspin.skillbase.catalog.infrastructure.search;

import com.headspin.skillbase.common.infrastructure.search.CommonSearchProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogSearchProviderDummy extends CommonSearchProviderDummy {

    @Inject
    public CatalogSearchProviderDummy() {
    }
}
