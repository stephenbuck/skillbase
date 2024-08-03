package com.headspin.skillbase.catalog.providers;

/**
 * Catalog features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CatalogFeaturesProvider {

    public boolean evaluateBoolean(String key, boolean def);
    
    public void test();

}
