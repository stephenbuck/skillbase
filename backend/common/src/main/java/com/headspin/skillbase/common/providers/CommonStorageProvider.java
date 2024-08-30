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

    public String uploadObject(InputStream input, Long size) throws Exception;

    public InputStream downloadObject(String id) throws Exception;

    public void deleteObject(String id) throws Exception;

    public void test();
    
}
