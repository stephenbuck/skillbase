package com.headspin.skillbase.common.providers;

import java.util.List;

/**
 * Common search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonSearchProvider {

    public List<String> search(String keyword, String sort, Integer offset, Integer limit);

    public void test();
    
}
