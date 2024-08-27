package com.headspin.skillbase.catalog.infrastructure.storage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.messages.Item;

/**
 * Minio implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogStorageProviderDefault implements CommonStorageProvider {

    // Config: com.headspin.skillbase.storage.files.root

    private final FileSystem fsys;
    private final String root;

    private final String endpoint = "http://minio1:9000";
    private final String accessKey = "zHOLGqmBHrUTd2Db08eM";
    private final String secretKey = "kDgcbKHjiqVTHXgRbMWUQ0TwiZDnjxbV3yC901aT";
    private final String bucket = "skillbase";
    private final MinioClient minio;

    public CatalogStorageProviderDefault() throws Exception {

        this.fsys = FileSystems.getDefault();
        this.root = "/";

        this.minio = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();

        if (!minio.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minio.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    private void go() throws Exception {
        String id = String.valueOf(UUID.randomUUID());
        InputStream s = new ByteArrayInputStream("Hello World".getBytes());

        uploadObject(id, s, Long.valueOf(id.length()));
        try (InputStream i = downloadObject(id)) {
            log.info("BINGO");
        }
        List<String> objs = listObjects();
        objs.forEach(log::info);
    }

    @Override
    public void uploadObject(String id, InputStream input, Long size) throws Exception {
        minio.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(id)
                        .stream(input, -1, 50000000L)
                        .build());
    }

    @Override
    public InputStream downloadObject(String id) throws Exception {
            return minio.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(id)
                            .build());
    }

    @Override
    public void deleteObject(String id) throws Exception {
        minio.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket).object(id).build());
    }

    @Override
    public List<String> listObjects() throws Exception {
        Iterable<Result<Item>> ires = minio.listObjects(ListObjectsArgs.builder().bucket(bucket).build());
        List<String> out = new ArrayList<String>();
        for (Result<Item> res : ires) {
            out.add(res.get().objectName());
        }
        return out;
    }

    @Override
    public Path resolvePath(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid) {
        return fsys.getPath(root, String.valueOf(homeUuid), String.valueOf(srcUuid));
    }

    @Override
    public File resolveFile(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid) {
        return fsys.getPath(root, String.valueOf(homeUuid), String.valueOf(srcUuid)).toFile();
    }

    @Override
    public void upload(@NotNull final UUID homeUuid, @NotNull final InputStream srcStream, @NotNull final UUID dstUuid)
            throws IOException {
        final Path dstPath = resolvePath(homeUuid, dstUuid);
        Files.copy(srcStream, dstPath, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @Override
    public void download(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid,
            @NotNull final OutputStream dstStream) throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        Files.copy(srcPath, dstStream);
    }

    @Override
    public void copy(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid)
            throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        final Path dstPath = resolvePath(homeUuid, dstUuid);
        Files.copy(srcPath, dstPath, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @Override
    public void move(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid)
            throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        final Path dstPath = resolvePath(homeUuid, dstUuid);
        Files.move(srcPath, dstPath, StandardCopyOption.ATOMIC_MOVE);
    }

    @Override
    public void delete(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid) throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        Files.delete(srcPath);
    }

    @Override
    public void rename(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid)
            throws IOException {
        final File srcFile = resolveFile(homeUuid, srcUuid);
        final File dstFile = resolveFile(homeUuid, dstUuid);
        srcFile.renameTo(dstFile);
    }

    @Override
    public boolean exists(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid) {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        return Files.exists(srcPath);
    }

    @Override
    public List<String> list(@NotNull final UUID homeUuid, @NotNull final UUID dirUuid) throws IOException {
        final Path dirPath = resolvePath(homeUuid, dirUuid);
        return Files
                .list(dirPath)
                .filter(Predicate.not(Files::isDirectory))
                .map(String::valueOf)
                .toList();
    }

    @Override
    public void test() {
        log.info("test:");

        try {
            go();
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }

        log.info("Stores:");
        for (FileStore store : fsys.getFileStores()) {
            log.info(store.name() + " - " + store.type());
        }

        log.info("Roots:");
        for (Path directory : fsys.getRootDirectories()) {
            boolean readable = Files.isReadable(directory);
            System.out.println("directory = " + directory + " - " + readable);
        }
    }
}
