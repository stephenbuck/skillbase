package com.headspin.skillbase.common.providers;

import java.util.List;

import jakarta.validation.constraints.NotNull;

/**
 * Common search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonSearchProvider {
    List<String> search(@NotNull final String keyword, final String sort, final Integer offset,
            final Integer limit);

    void test();
}
