package com.headspin.skillbase.member.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the Member files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberStorageProviderDefault implements CommonStorageProvider {

    private final String endpoint = "http://minio1:9000";
    private final String accessKey = "zHOLGqmBHrUTd2Db08eM";
    private final String secretKey = "kDgcbKHjiqVTHXgRbMWUQ0TwiZDnjxbV3yC901aT";
    private final String bucket = "skillbase";
    private final MinioClient minio;

    public MemberStorageProviderDefault() throws Exception {
        this.minio = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }
    
    @Override
    @Transactional
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception {
        String id = String.valueOf(UUID.randomUUID());
        minio.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(id)
                        .stream(input, size, 50000000L)
                        .build());
        return id;
    }

    @Override
    public InputStream downloadObject(@NotNull final String id) throws Exception {
        return minio.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(id)
                        .build());
    }

    @Override
    @Transactional
    public void deleteObject(@NotNull final String id) throws Exception {
        minio.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(id)
                        .build());
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
