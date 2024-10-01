package com.headspin.skillbase.image.infrastructure.events;

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
public class ImageEventsProviderPulsar extends CommonEventsProviderPulsar {

    @Inject
    public ImageEventsProviderPulsar(
            @ConfigProperty(name = "com.headspin.skillbase.image.events.pulsar.bootstraps") final String configBootstraps,
            @ConfigProperty(name = "com.headspin.skillbase.image.events.pulsar.subscriber") final String configSubscriber) {
        super(configBootstraps, configSubscriber);
    }
}
