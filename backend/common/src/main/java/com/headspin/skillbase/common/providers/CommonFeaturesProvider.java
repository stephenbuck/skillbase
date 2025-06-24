package com.headspin.skillbase.common.providers;

import jakarta.validation.constraints.NotNull;

/**
 * Common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonFeaturesProvider {

    boolean evaluateBoolean(@NotNull final String key, final boolean def);

    void test();

}
