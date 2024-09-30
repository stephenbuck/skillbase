package com.headspin.skillbase.image.infrastructure.storage;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderSeaweedFS;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * SeaweedFS implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageStorageProviderSeaweedFS extends CommonStorageProviderSeaweedFS {

    @Inject
    public ImageStorageProviderSeaweedFS(
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.seaweedfs.host") final String configHost,
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.seaweedfs.port") final Integer configPort,
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.seaweedfs.root") final String configRoot)
            throws Exception {
        super(configHost, configPort, configRoot);
    }
}
