package com.headspin.skillbase.common.providers;

/**
 * Common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonCacheProvider {

    String get(String key) throws Exception;

    boolean set(String key, String object) throws Exception;

    boolean touch(String key) throws Exception;

    boolean exists(String key) throws Exception;

    boolean delete(String key) throws Exception;

    void test();
}
