package com.headspin.skillbase.common.providers;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;

/**
 * Common config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonConfigProvider {

    Optional<?> getOptionalValue(@NotNull final String key, @NotNull final Class<?> type);

    void test();

}
