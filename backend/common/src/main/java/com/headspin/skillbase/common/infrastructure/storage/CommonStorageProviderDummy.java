package com.headspin.skillbase.common.infrastructure.storage;

import java.io.InputStream;

import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common files provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonStorageProviderDummy implements CommonStorageProvider {

    @Inject
    public CommonStorageProviderDummy() {
    }

    @Override
    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size,
            @NotNull final MediaType type) throws Exception {
        return null;
    }

    @Override
    public CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception {
        return null;
    }

    @Override
    public void deleteObject(@NotNull final String object_id) throws Exception {
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
