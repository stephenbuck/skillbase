package com.headspin.skillbase.image.infrastructure.storage;

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
 * Minio implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class ImageStorageProviderDefault implements CommonStorageProvider {

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.image.minio.endpoint")
    private String configEndpoint;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.image.minio.bucket")
    private String configBucket;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.image.minio.access")
    private String configAccess;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.image.minio.secret")
    private String configSecret;

    private final MinioClient minio;

    public ImageStorageProviderDefault() throws Exception {
        this.minio = MinioClient.builder()
                .endpoint(configEndpoint)
                .credentials(configAccess, configSecret)
                .build();
        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(configBucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(configBucket).build());
        }
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception {
        String image_id = String.valueOf(UUID.randomUUID());
        minio.putObject(
                PutObjectArgs.builder()
                        .bucket(configBucket)
                        .object(image_id)
                        .stream(input, size, 50000000L)
                        .build());
        return image_id;
    }

    @Override
    public InputStream downloadObject(@NotNull final String image_id) throws Exception {
        return minio.getObject(
                GetObjectArgs.builder()
                        .bucket(configBucket)
                        .object(image_id)
                        .build());
    }

    @Override
    public void deleteObject(@NotNull final String image_id) throws Exception {
        minio.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(configBucket)
                        .object(image_id)
                        .build());
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
