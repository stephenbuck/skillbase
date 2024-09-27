package com.headspin.skillbase.workflow.infrastructure.storage;

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
public class WorkflowStorageProviderMinIO extends CommonStorageProviderMinIO {

    @Inject
    public WorkflowStorageProviderMinIO(
            @ConfigProperty(name = "com.headspin.skillbase.workflow.storage.minio.endpoint") final String configEndpoint,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.storage.minio.bucket") final String configBucket,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.storage.minio.access") final String configAccess,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.storage.minio.secret") final String configSecret)
            throws Exception {
        super(configEndpoint, configBucket, configAccess, configSecret);
    }
}
