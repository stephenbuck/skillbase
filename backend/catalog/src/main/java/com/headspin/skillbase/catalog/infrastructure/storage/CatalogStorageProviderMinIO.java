package com.headspin.skillbase.catalog.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;

/**
 * MinIO implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogStorageProviderMinIO implements CommonStorageProvider {

    private final String bucket;
    private final MinioClient minio;

    @Inject
    public CatalogStorageProviderMinIO(
        @ConfigProperty(name = "com.headspin.skillbase.catalog.minio.endpoint") String configEndpoint,        
        @ConfigProperty(name = "com.headspin.skillbase.catalog.minio.bucket") String configBucket,
        @ConfigProperty(name = "com.headspin.skillbase.catalog.minio.access") String configAccess,
        @ConfigProperty(name = "com.headspin.skillbase.catalog.minio.secret") String configSecret
    ) throws Exception {
        this.bucket = configBucket;
        this.minio = MinioClient.builder()
                .endpoint(configEndpoint)
                .credentials(configAccess, configSecret)
                .build();
        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception {
        final String object_id = String.valueOf(UUID.randomUUID());
        minio.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(object_id)
                        .stream(input, size, 50000000L)
                        .build());
        return object_id;
    }

    @Override
    public InputStream downloadObject(@NotNull final String object_id) throws Exception {
        return minio.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(object_id)
                        .build());
    }

    @Override
    public void deleteObject(@NotNull final String object_id) throws Exception {
        minio.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(object_id)
                        .build());
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
