package com.headspin.skillbase.common.providers;

import java.io.InputStream;

import jakarta.validation.constraints.NotNull;

/**
 * Common storage provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonStorageProvider {

    public String uploadObject(@NotNull final InputStream input, @NotNull final Long size) throws Exception;

    public InputStream downloadObject(@NotNull final String object_id) throws Exception;

    public void deleteObject(@NotNull final String object_id) throws Exception;

    public void test();
    
}
