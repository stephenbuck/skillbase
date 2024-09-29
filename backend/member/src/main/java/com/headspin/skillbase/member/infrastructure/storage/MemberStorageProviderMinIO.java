package com.headspin.skillbase.member.infrastructure.storage;

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
public class MemberStorageProviderMinIO extends CommonStorageProviderMinIO {

    @Inject
    public MemberStorageProviderMinIO(
            @ConfigProperty(name = "com.headspin.skillbase.member.storage.minio.endpoint") final String configEndpoint,
            @ConfigProperty(name = "com.headspin.skillbase.member.storage.minio.bucket") final String configBucket,
            @ConfigProperty(name = "com.headspin.skillbase.member.storage.minio.access") final String configAccess,
            @ConfigProperty(name = "com.headspin.skillbase.member.storage.minio.secret") final String configSecret)
            throws Exception {
        super(configEndpoint, configBucket, configAccess, configSecret);
    }
}
