package com.headspin.skillbase.common.providers;

/**
 * Common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonRegistryProvider {
    public String lookup(String key) throws Exception;

    public boolean register(String key, String val) throws Exception;

    public boolean delete(String key) throws Exception;

    public void test();
}