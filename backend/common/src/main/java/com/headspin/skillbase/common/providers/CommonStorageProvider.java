package com.headspin.skillbase.common.providers;

import java.io.InputStream;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;

/**
 * Common storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonStorageProvider {

    class CommonStorageObject {

        public String object_id;
        public String type;
        public Long size;
        public InputStream input;

        public CommonStorageObject(
                String object_id,
                String type,
                Long size,
                InputStream input) {
            this.object_id = object_id;
            this.type = type;
            this.size = size;
            this.input = input;
        }
    }

    String uploadObject(@NotNull final InputStream input, @NotNull final Long size,
            @NotNull final MediaType type) throws Exception;

    CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception;

    void deleteObject(@NotNull final String object_id) throws Exception;

    void test();
}
