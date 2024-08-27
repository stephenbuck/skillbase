package com.headspin.skillbase.member.infrastructure.storage;


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
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.enterprise.context.ApplicationScoped;
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

    // Config: com.headspin.skillbase.storage.files.root

    private final FileSystem fsys;
    private final String root;

    public MemberStorageProviderDefault() {
        this.fsys = FileSystems.getDefault();
        this.root = "/";
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

    @Override
    public void uploadObject(String id, InputStream input, Long size) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadObject'");
    }

    @Override
    public InputStream downloadObject(String id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'downloadObject'");
    }

    @Override
    public void deleteObject(String id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteObject'");
    }

    @Override
    public List<String> listObjects() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listObjects'");
    }
}
