package com.headspin.skillbase.common.providers;

/**
 * Common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonCacheProvider {

    public String get(String key) throws Exception;

    public boolean set(String key, String object) throws Exception;

    public boolean touch(String key) throws Exception;

    public boolean exists(String key) throws Exception;

    public boolean delete(String key) throws Exception;

    public void test();
}
