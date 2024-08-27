package com.headspin.skillbase.common.providers;

import java.util.Optional;

/**
 * Common config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonConfigProvider {

    public Optional<?> getOptionalValue(String key, Class<?> type);

    public void test();
    
}
