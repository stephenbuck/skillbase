package com.headspin.skillbase.storage.infrastructure.files;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.headspin.skillbase.storage.providers.StorageFilesProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the Storage files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class StorageFilesProviderDefault implements StorageFilesProvider {

    // Config: com.headspin.skillbase.storage.files.url
    private static final String configUrl = "sqlite3://myjfs.db";

    // Config: com.headspin.skillbase.storage.files.root
    private static final String configRoot = "/";

    private final URI url;
    private final FileSystem fsys;
    private final String root;

    public StorageFilesProviderDefault() {
        this.url = URI.create(configUrl);
        this.fsys = FileSystems.getFileSystem(url);
        this.root = configRoot;
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
    public void upload(@NotNull final UUID homeUuid, @NotNull final InputStream srcStream, @NotNull final UUID dstUuid) throws IOException {
        final Path dstPath = resolvePath(homeUuid, dstUuid);
        Files.copy(srcStream, dstPath, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @Override
    public void download(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final OutputStream dstStream) throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        Files.copy(srcPath, dstStream);
    }

    @Override
    public void copy(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException {
        final Path srcPath = resolvePath(homeUuid, srcUuid);
        final Path dstPath = resolvePath(homeUuid, dstUuid);
        Files.copy(srcPath, dstPath, StandardCopyOption.COPY_ATTRIBUTES);
    }

    @Override
    public void move(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException {
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
    public void rename(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException {
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

        log.info("Stores:");
        for (FileStore store: fsys.getFileStores()) {
            log.info(store.name() + " - " + store.type());
        }

        log.info("Roots:");
        for (Path directory : fsys.getRootDirectories()) {
            boolean readable = Files.isReadable(directory);
            System.out.println("directory = " + directory + " - " + readable);
        }
    }
}
