package com.headspin.skillbase.common.providers;

/**
 * Common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonFeaturesProvider {

    public boolean evaluateBoolean(String key, boolean def);
    
    public void test();

}
