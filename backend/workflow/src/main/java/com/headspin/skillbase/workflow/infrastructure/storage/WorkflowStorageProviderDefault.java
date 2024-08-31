package com.headspin.skillbase.workflow.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the Member storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowStorageProviderDefault implements CommonStorageProvider {

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.workflow.minio.endpoint")
    private String configEndpoint;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.workflow.minio.bucket")
    private String configBucket;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.workflow.minio.access")
    private String configAccess;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.workflow.minio.secret")
    private String configSecret;

    private final MinioClient minio;

    public WorkflowStorageProviderDefault() throws Exception {
        this.minio = MinioClient.builder()
                .endpoint(configEndpoint)
                .credentials(configAccess, configSecret)
                .build();
        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(configBucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(configBucket).build());
        }
    }
    
    @Override
    @Transactional
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception {
        String object_id = String.valueOf(UUID.randomUUID());
        minio.putObject(
                PutObjectArgs.builder()
                        .bucket(configBucket)
                        .object(object_id)
                        .stream(input, size, 50000000L)
                        .build());
        return object_id;
    }

    @Override
    public InputStream downloadObject(@NotNull final String object_id) throws Exception {
        return minio.getObject(
                GetObjectArgs.builder()
                        .bucket(configBucket)
                        .object(object_id)
                        .build());
    }

    @Override
    @Transactional
    public void deleteObject(@NotNull final String object_id) throws Exception {
        minio.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(configBucket)
                        .object(object_id)
                        .build());
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
