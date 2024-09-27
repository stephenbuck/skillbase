package com.headspin.skillbase.common.infrastructure.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

/**
 * JuiceFS implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonStorageProviderJuiceFS implements CommonStorageProvider {

    private final String object_root;

    @Inject
    public CommonStorageProviderJuiceFS(
            final String configRoot)
            throws Exception {
        this.object_root = configRoot;
    }

    private File getObjectPath(String object_id) {
        return new File(object_root, object_id);
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size,
            @NotNull final MediaType type) throws Exception {
        final String object_id = String.valueOf(UUID.randomUUID());
        final File object_path = getObjectPath(object_id);
        try (FileOutputStream output = new FileOutputStream(object_path)) {
            input.transferTo(output);
            return object_id;
        }
    }

    @Override
    public CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception {
        final File object_path = getObjectPath(object_id);
        final FileInputStream input = new FileInputStream(object_path);
        return new CommonStorageObject(
                object_id,
                null,
                null,
                input);
    }

    @Override
    public void deleteObject(@NotNull final String object_id) throws Exception {
        File object_path = getObjectPath(object_id);
        object_path.delete();
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
