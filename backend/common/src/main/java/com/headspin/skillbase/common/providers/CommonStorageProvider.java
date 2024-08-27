package com.headspin.skillbase.common.providers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Common storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonStorageProvider {

    public Path resolvePath(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid);

    public File resolveFile(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid);

    public void upload(@NotNull final UUID homeUuid, @NotNull final InputStream srcStream, @NotNull final UUID dstUuid) throws IOException;

    public void download(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final OutputStream dstStream) throws IOException;

    public void copy(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException;

    public void move(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException;

    public void delete(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid) throws IOException;

    public void rename(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid, @NotNull final UUID dstUuid) throws IOException;

    public boolean exists(@NotNull final UUID homeUuid, @NotNull final UUID srcUuid);

    public List<String> list(@NotNull final UUID homeUuid, @NotNull final UUID dirUuid) throws IOException;




    public void uploadObject(String id, InputStream input, Long size) throws Exception;

    public InputStream downloadObject(String id) throws Exception;

    public void deleteObject(String id) throws Exception;

    public List<String> listObjects() throws Exception;


    
    public void test();
    
}
