package com.headspin.skillbase.catalog.infrastructure.events;

import com.headspin.skillbase.common.infrastructure.events.CommonEventsProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogEventsProviderDummy extends CommonEventsProviderDummy {

    @Inject
    public CatalogEventsProviderDummy() {
    }
}
