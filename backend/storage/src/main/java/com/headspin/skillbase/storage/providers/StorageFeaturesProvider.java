package com.headspin.skillbase.storage.providers;

/**
 * Storage features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface StorageFeaturesProvider {

    public boolean evaluateBoolean(String key, boolean def);
    
    public void test();

}
