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

    public static class CommonStorageObject {

        public String object_id;
        public String type;
        public Long size;
        public InputStream input;

        public CommonStorageObject(
            String object_id,
            String type,
            Long size,
            InputStream input
        ) {
            this.object_id = object_id;
            this.type = type;
            this.size = size;
            this.input = input;
        }
    }

    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type) throws Exception;

    public CommonStorageObject downloadObject(@NotNull final String object_id) throws Exception;

    public void deleteObject(@NotNull final String object_id) throws Exception;

    public void test();
    
}
