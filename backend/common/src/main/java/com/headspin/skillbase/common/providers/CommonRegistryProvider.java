package com.headspin.skillbase.common.providers;

/**
 * Common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonRegistryProvider {
    String lookup(String key) throws Exception;

    boolean register(String key, String val) throws Exception;

    boolean delete(String key) throws Exception;

    void test();
}
