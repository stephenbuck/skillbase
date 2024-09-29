package com.headspin.skillbase.catalog.infrastructure.storage;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.storage.CommonStorageProviderMinIO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * MinIO implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogStorageProviderMinIO extends CommonStorageProviderMinIO {

    @Inject
    public CatalogStorageProviderMinIO(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.storage.minio.endpoint") final String configEndpoint,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.storage.minio.bucket") final String configBucket,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.storage.minio.access") final String configAccess,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.storage.minio.secret") final String configSecret)
            throws Exception {
        super(configEndpoint, configBucket, configAccess, configSecret);
    }
}
