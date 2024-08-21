package com.headspin.skillbase.storage.providers;

import java.util.Optional;

/**
 * Storage config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface StorageConfigProvider {

    public Optional<?> getOptionalValue(String key, Class<?> type);

    public void test();
    
}
