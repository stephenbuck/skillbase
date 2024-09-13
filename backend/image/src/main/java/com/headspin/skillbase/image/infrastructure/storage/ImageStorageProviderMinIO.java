package com.headspin.skillbase.image.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
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
public class ImageStorageProviderMinIO implements CommonStorageProvider {

    private final String bucket;
    private final MinioClient minio;

    @Inject
    public ImageStorageProviderMinIO(
        @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.endpoint") final String configEndpoint,
        @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.bucket") final String configBucket,
        @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.access") final String configAccess,
        @ConfigProperty(name = "com.headspin.skillbase.image.storage.minio.secret") final String configSecret
    ) throws Exception {
        this.bucket = configBucket;
        this.minio = MinioClient.builder()
            .endpoint(configEndpoint)
            .credentials(configAccess, configSecret)
            .build();
        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(configBucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(configBucket).build());
        }
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type) throws Exception {
        String object_id = String.valueOf(UUID.randomUUID());
        minio.putObject(
            PutObjectArgs.builder()
                .bucket(bucket)
                .object(object_id)
                .stream(input, size, PutObjectArgs.MAX_PART_SIZE)
                .contentType(String.valueOf(type))
                .build());
        return object_id;
    }

    @Override
    public CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception {
        final GetObjectResponse resp = minio.getObject(
            GetObjectArgs.builder()
                .bucket(bucket)
                .object(object_id)
                .build());
        return new CommonStorageObject(
            object_id,
            resp.headers().get(HttpHeaders.CONTENT_TYPE),
            Long.valueOf(resp.headers().get(HttpHeaders.CONTENT_TYPE)),
            resp);
    }

    @Override
    public void deleteObject(@NotNull final String image_id) throws Exception {
        minio.removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(image_id)
                .build());
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
