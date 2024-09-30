package com.headspin.skillbase.workflow.infrastructure.events;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.events.CommonEventsProviderKafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka implementation of the common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogEventsProviderKafka extends CommonEventsProviderKafka {

    @Inject
    public CatalogEventsProviderKafka(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.events.kafka.bootstraps") final String configBootstraps,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.events.kafka.clientid") final String configClientId,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.events.kafka.groupid") final String configGroupId) {
                super(configBootstraps, configClientId, configGroupId);
    }
}
