package com.headspin.skillbase.common.infrastructure.storage;

import java.io.InputStream;
import java.util.UUID;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import seaweedfs.client.FilerClient;
import seaweedfs.client.SeaweedInputStream;
import seaweedfs.client.SeaweedOutputStream;

/**
 * SeaweedFS implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonStorageProviderSeaweedFS implements CommonStorageProvider {

    private final FilerClient filer;
    private final String object_root;

    @Inject
    public CommonStorageProviderSeaweedFS(
            final String configHost,
            final Integer configPort,
            final String configRoot)
            throws Exception {
        this.filer = new FilerClient(configHost, configPort);
        this.object_root = configRoot;
    }

    private String getObjectPath(final String object_id) {
        return object_root + "/" + object_id;
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size,
            @NotNull final MediaType type) throws Exception {
        final String object_id = String.valueOf(UUID.randomUUID());
        final String object_path = getObjectPath(object_id);
        try (SeaweedOutputStream output = new SeaweedOutputStream(filer, object_path)) {
            input.transferTo(output);
            return object_id;
        }
    }

    @Override
    public CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception {
        final String object_path = getObjectPath(object_id);
        final SeaweedInputStream input = new SeaweedInputStream(filer, object_path);
        return new CommonStorageObject(
                object_id,
                null,
                null,
                input);
    }

    @Override
    public void deleteObject(@NotNull final String object_id) throws Exception {
        final String object_path = getObjectPath(object_id);
        filer.rm(object_path, false, false);
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
