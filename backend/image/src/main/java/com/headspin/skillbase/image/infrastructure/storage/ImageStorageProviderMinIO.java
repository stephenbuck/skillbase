package com.headspin.skillbase.image.infrastructure.storage;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderMinIO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * MinIO implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class ImageStorageProviderMinIO extends CommonStorageProviderMinIO {

    @Inject
    public ImageStorageProviderMinIO(
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.endpoint") final String configEndpoint,
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.bucket") final String configBucket,
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.access") final String configAccess,
            @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.secret") final String configSecret)
            throws Exception {
        super(configEndpoint, configBucket, configAccess, configSecret);
    }
}
