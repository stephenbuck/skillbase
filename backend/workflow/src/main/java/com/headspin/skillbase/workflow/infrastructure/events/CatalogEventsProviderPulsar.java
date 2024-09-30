package com.headspin.skillbase.workflow.infrastructure.events;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.events.CommonEventsProviderPulsar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Pulsar implementation of the common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogEventsProviderPulsar extends CommonEventsProviderPulsar {

    @Inject
    public CatalogEventsProviderPulsar(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.events.pulsar.bootstraps") final String configBootstraps,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.events.pulsar.subscriber") final String configSubscriber) {
        super(configBootstraps, configSubscriber);
    }
}
