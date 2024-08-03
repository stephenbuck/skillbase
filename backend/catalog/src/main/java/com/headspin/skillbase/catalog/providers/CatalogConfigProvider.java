package com.headspin.skillbase.catalog.providers;

import java.util.Optional;

/**
 * Catalog config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CatalogConfigProvider {

    public Optional<?> getOptionalValue(String key, Class<?> type);

    public void test();
    
}
